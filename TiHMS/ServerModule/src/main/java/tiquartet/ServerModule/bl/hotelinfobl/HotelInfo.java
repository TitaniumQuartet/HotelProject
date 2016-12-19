package tiquartet.ServerModule.bl.hotelinfobl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.ReviewDataImpl;
import tiquartet.ServerModule.dataservice.impl.RoomDataImpl;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.ReviewPO;
import tiquartet.ServerModule.po.RoomTypePO;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.blservice.hotelinfoblservice.HotelInfoBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderNumVO;

/**
 * @author 李珍鸿
 *
 */
/**
 * @author 李珍鸿
 *
 */
public class HotelInfo implements HotelInfoBLService {
	HotelInfoDataImpl hoteldataimpl;
	ReviewDataImpl reviewdataimpl;
	RoomDataImpl roomdataimpl;
	ManageOrderController manageordercontroller;

	public HotelInfo() {
		hoteldataimpl = HotelInfoDataImpl.getInstance();
		reviewdataimpl = ReviewDataImpl.getInstance();
		roomdataimpl = RoomDataImpl.getInstance();
		manageordercontroller = new ManageOrderController();
	}
    //获得酒店简单信息
	public HotelBriefVO getHotelBrief(int hotelID, int userID) throws RemoteException {
		HotelInfoPO hp = hoteldataimpl.getHotelInfo(hotelID);
		if (hp == null) {
			return null;
		}
		HotelBriefVO hotelbrief = hp.getBriefVO();
		OrderNumVO onp = manageordercontroller.numAtHotel(hotelID, userID);
		if (onp == null) {
			hotelbrief.numOfAllOrder = 0;
			hotelbrief.numOfExecutedOrder = 0;
		} else {
			hotelbrief.numOfAllOrder = onp.allOrder;
			hotelbrief.numOfExecutedOrder = onp.executedOrder;
		}
		return hotelbrief;
	}

	/* 
     * 获取酒店详细信息
	 */
	public HotelDetailsVO getHotelDetails(int hotelID, int userID) throws RemoteException {
		HotelDetailsVO hoteldetails = new HotelDetailsVO();
		HotelInfoPO hp = hoteldataimpl.getHotelInfo(hotelID);
		if (hp == null) {
			return null;
		}
		hoteldetails.address = hp.getaddress();
		hoteldetails.averageg = hp.getaverageGrade();
		hoteldetails.circleName = hp.getcircleName();
		hoteldetails.cityName = hp.getcityName();
		hoteldetails.hotelID = hp.gethotelId();
		hoteldetails.introduction = hp.gethotelIntroduction();
		hoteldetails.lowprice = hp.getlowprice();
		hoteldetails.hotelName = hp.gethotelName();
		hoteldetails.serviceintro = hp.getserviceIntroduction();
		hoteldetails.star = hp.getstar();
		List<ReviewPO> list = reviewdataimpl.searchByHotel(hotelID);
		for (int i = 0; i < list.size(); i++) {
			ReviewVO rv = new ReviewVO();
			rv = list.get(i).toReviewvo();
			hoteldetails.reviewList.add(rv);

		}
		return hoteldetails;
	}
	/* 
	 * 可用房型
	 */
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder) throws RemoteException {
		List<RoomTypePO> polist = roomdataimpl.availableRoomType(preOrder.hotelID, preOrder.startTime, preOrder.leaveTime, preOrder.numOfRoom);
		List<RoomTypeVO> volist = new ArrayList<RoomTypeVO>();
		for (int i = 0; i < polist.size(); i++) {
			RoomTypeVO rtv = polist.get(i).toRoomTypevo();
			volist.add(rtv);
		}
		return volist;

	}

	/* 
	 * 评论酒店
	 */
	public ResultMessage reviewHotel(ReviewVO review) throws RemoteException {
		ReviewPO reviewpo = new ReviewPO(review);
		return reviewdataimpl.insert(reviewpo);
	}
    
	/* 
	 * 修改酒店信息
	 */
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo) throws RemoteException {
		HotelInfoPO hip = new HotelInfoPO(hotelInfo);
		return hoteldataimpl.update(hip);
	}
    
	/* 
	 * 预定过得酒店列表
	 */
	public List<HotelBriefVO> clientHotelList(int userID) throws RemoteException {
		List<HotelBriefVO> volist = new ArrayList<HotelBriefVO>();
		List<Integer> hotelID = manageordercontroller.orderedHotelID(userID);
		for (int i = 0; i < hotelID.size(); i++) {
			HotelBriefVO hotelbriefvo = this.getHotelBrief(hotelID.get(i), userID);
			volist.add(hotelbriefvo);
		}
		return volist;
	}
}
