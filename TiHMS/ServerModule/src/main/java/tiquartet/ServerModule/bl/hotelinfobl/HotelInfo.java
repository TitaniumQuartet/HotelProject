package tiquartet.ServerModule.bl.hotelinfobl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.ServerModule.bl.usermainbl.MockUserMain;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrder;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

public class HotelInfo {
	
	public HotelInfo(){
		MockUserMain userMain=new MockUserMain();
		userMain.currentUser();
	}
	public HotelBriefVO getHotelBrief (int hotelID){
		HotelBriefVO hotelbrief=new HotelBriefVO();
		hotelbrief.hotelID=hotelID;
		return hotelbrief;
	}
	
	public HotelDetailsVO getHotelDetails (int hotelID){
	    ManageOrder order=new ManageOrder();
	    order.clientAtHotel (hotelID);
	    HotelDetailsVO hoteldetails=new HotelDetailsVO();
	    hoteldetails.hotelID=hotelID;
		return hoteldetails;
	}
	
	public List<RoomTypeVO> availableRoomType (PreOrderVO preOrder){
		RoomTypeVO room1=new RoomTypeVO();
		room1.roomType=001;
		room1.name="单人房";
		room1.price=100;
		RoomTypeVO room2=new RoomTypeVO();
		room2.roomType=002;
		room2.name="家庭房";
		room2.price=200;
		ArrayList<RoomTypeVO> roomlist=new ArrayList<RoomTypeVO>();
		roomlist.add(room1);
		roomlist.add(room2);
		return roomlist;
	}
	
	public ResultMessage reviewHotel(ReviewVO review){
		return new ResultMessage(true);
	}
	
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo){
		return new ResultMessage(true);
	}

}
