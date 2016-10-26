package tiquartet.ServerModule.data.roomdata;

import java.util.*;
import tiquartet.CommonModule.dataservice.roomdataservice.*;
import tiquartet.CommonModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;


public class RoomData_stub implements RoomDataService{
	public ResultMessage update(RoomPO room){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage insert(RoomPO room){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage delete(int roomID){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public boolean isAvailable(Calendar start, Calendar end, int roomTypeID, int numOfRoom){
		System.out.println("yes");
		return true;
	}
	public ResultMessage checkIn (int roomID){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage checkOut (int roomID){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}


}
