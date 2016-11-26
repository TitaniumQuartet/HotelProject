package tiquartet.ServerModule.dataservice.roomdataservice;

import java.util.List;

import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.OrderPO;

public class RoomDataController{
	
	RoomDataHelper helper;

	public static ResultMessage preOrder (OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage cancelPreOrder (OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<RoomPO> getRoom (long hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List <RoomTypePO> availableRoomType (int hotelID, String startDate, String endDate, int numOfRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage update(RoomPO room) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage insert(RoomPO room) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage delete(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage checkIn (int roomID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage insertType (int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage updateType (int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}
}
