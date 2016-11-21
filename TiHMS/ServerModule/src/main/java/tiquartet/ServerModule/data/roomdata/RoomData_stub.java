package tiquartet.ServerModule.data.roomdata;

import java.util.*;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.roomdataservice.*;
import tiquartet.ServerModule.po.*;


public class RoomData_stub implements RoomDataService{
	public ResultMessage update(RoomPO room){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage insert(RoomPO room){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage delete(int roomID){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public boolean isAvailable(Calendar start, Calendar end, int roomTypeID, int numOfRoom){
		System.out.println("yes");
		return true;
	}
	public ResultMessage checkIn (int roomID){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage checkOut (int roomID){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage preOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate,
			String endDate, int numOfRoom) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage insertType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage updateType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}


}
