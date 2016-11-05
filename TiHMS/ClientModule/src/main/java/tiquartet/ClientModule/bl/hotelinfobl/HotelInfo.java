package tiquartet.ClientModule.bl.hotelinfobl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tiquartet.ClientModule.bl.usermainbl.MockUserMain;
import tiquartet.ClientModule.bl.manageorderbl.MockManageOrder;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfo {
	
	HotelInfo(){
		MockUserMain userMain=new MockUserMain();
		userMain.currentUser();
	}
	public HotelBriefVO getHotelBrief (int hotelID){
		return new HotelBriefVO();
	}
	public HotelDetailsVO getHotelDetails (int hotelID){
	    MockManageOrder order=new MockManageOrder();
	    order.clientAtHotel (hotelID);
		return new HotelDetailsVO();
	}
	public List<RoomTypeVO> availableRoomType (PreOrderVO preOrder){
		return new ArrayList<RoomTypeVO>();
	}
	public ResultMessage reviewHotel(ReviewVO review){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo){
		return ResultMessage.SUCCEED;
	}

}
