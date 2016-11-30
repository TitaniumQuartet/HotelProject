package tiquartet.ServerModule.bl.hotelinfobl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.ServerModule.datahelper.DataFactory;
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
public class HotelInfo implements HotelInfoBLService{
	HotelInfoDataImpl hoteldataimpl;
	ReviewDataImpl reviewdataimpl;
	RoomDataImpl roomdataimpl;
	ManageOrderController manageordercontroller;
	public HotelInfo(){
		hoteldataimpl=new HotelInfoDataImpl();
		reviewdataimpl=new ReviewDataImpl();
		roomdataimpl=new RoomDataImpl();
		manageordercontroller=new ManageOrderController();
	}
	public HotelBriefVO getHotelBrief (int hotelID,int userID)throws RemoteException{
	    //传入酒店ID返回酒店的简略信息.
		HotelBriefVO hotelbrief=new HotelBriefVO();
		HotelInfoPO hp=new HotelInfoPO();//此处 应该修改
		hotelbrief.avgragegrade=hp.getavgreagegrade();
		hotelbrief.circleName=hp.getcircleName();
		hotelbrief.cityName=hp.getcityName();
		hotelbrief.hotelID=hp.gethotelId();
		hotelbrief.hotelName=hp.gethotelName();
		hotelbrief.star=hp.getstar();
		OrderNumVO onp=manageordercontroller.numAtHotel(hotelID,userID);
		hotelbrief.numOfAllOrder=onp.allOrder;
		hotelbrief.numOfEndOrder=onp.executedOrder;
		return hotelbrief;
	}
	
		
	public HotelDetailsVO getHotelDetails (int hotelID,int userID)throws RemoteException{	
		//传入酒店编号，返回酒店详细信息
	    HotelDetailsVO hoteldetails=new HotelDetailsVO();
	    HotelInfoPO hp=new HotelInfoPO();//此处应该修改
	    hoteldetails.address=hp.getaddress();
	    hoteldetails.averageg=hp.getavgreagegrade();
	    hoteldetails.circleName=hp.getcircleName();
	    hoteldetails.cityName=hp.getcityName();
	    hoteldetails.hotelID=hp.gethotelId();
	    hoteldetails.introduction=hp.gethotelIntroduction();
	    hoteldetails.lowprice=hp.getlowprice();
	    hoteldetails.hotelName=hp.gethotelName();
	    hoteldetails.serviceintro=hp.getserviceIntroduction();
	    hoteldetails.star=hp.getstar();
	    List<ReviewPO> list=new ArrayList<ReviewPO>();//此处应该修改
	    for(int i=0;i<list.size();i++){
	    	ReviewVO rv=new ReviewVO();
	    	rv=list.get(i).toReviewvo();
	    	hoteldetails.list.add(rv);
	    	
	    }
		return hoteldetails;
	}
	
	public List<RoomTypeVO> availableRoomType (PreOrderVO preOrder)throws RemoteException{
		//传入入住日期，酒店，返回可用客房类型及该类型数量
		List<RoomTypePO> list=new ArrayList<RoomTypePO>();
		List<RoomTypeVO> listvo=new ArrayList<RoomTypeVO>();
		for(int i=0;i<list.size();i++){
			RoomTypeVO rtv=new RoomTypeVO();
			rtv.typeIntroduction=list.get(i).gettypeIntroduction();
			rtv.roomType=list.get(i).getroomType();
			rtv.price=list.get(i).getprice();
			rtv.roomTypeId=list.get(i).getroomTypeId();
			listvo.add(rtv);
		}
		return listvo;
		
	}
	
	public ResultMessage reviewHotel(ReviewVO review)throws RemoteException{
		//传入一个评价，把他存入数据库中
		ReviewPO reviewpo=new ReviewPO();
		reviewpo.setreview(review.review);
		reviewpo.sethotelId(review.hotelID);
		reviewpo.setscore(review.score);
		reviewpo.settime(review.time);
		reviewpo.setuserId(review.userID);
		reviewpo.setuserName(review.userName);
		//datafactory.getReviewDataHelper().insert(reviewpo);
		if(reviewpo.getreview()!=null){
			return new ResultMessage(true);			
		}
		return new ResultMessage(false);
	}
	
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo)throws RemoteException{
		//修改酒店信息
		HotelInfoPO hip=new HotelInfoPO();
		hip.setavgragegrade(hotelInfo.averagegrade);
		hip.setcircleId(hotelInfo.circleId);
		hip.setcircleName(hotelInfo.circleName);
		hip.setcityName(hotelInfo.cityName);
		hip.sethotelId(hotelInfo.hotelID);
		hip.sethotelName(hotelInfo.hotelName);
		hip.sethotelIntroduction(hotelInfo.hotelIntroduction);
		hip.setserviceIntroduction(hotelInfo.serviceIntroduction);
		hip.setstar(hotelInfo.star);
		//datafactory.getHotelInfoDataHelper().update(hip);
		return new ResultMessage(true);
	}
   public List<HotelBriefVO> clientHotelList(int userId)throws RemoteException{
	   //返回用户预订过的酒店列表
	   List<HotelBriefVO> listvo=new ArrayList<HotelBriefVO>();	   
	   ManageOrderController manageordercontroller=new ManageOrderController();
	   List<Integer>  hotelID=manageordercontroller.orderedHotelID(userId);
	   for(int i=0;i<hotelID.size();i++){
		   HotelInfoPO hip=new HotelInfoPO();//此处应该修改
		   HotelBriefVO hotelbriefvo =new HotelBriefVO();
		   hotelbriefvo.circleName=hip.getcircleName();
		   hotelbriefvo.cityName=hip.getcityName();
		   hotelbriefvo.hotelID=hip.gethotelId();
		   hotelbriefvo.star=hip.getstar();
		   hotelbriefvo.avgragegrade=hip.getavgreagegrade();
		   hotelbriefvo.hotelName=hip.gethotelName();
		   listvo.add(hotelbriefvo);
	   }
	   return listvo;
   }
}
