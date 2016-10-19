package tiquartet.CommonModule.dataservice.roomdataservice;

import java.util.Calendar;

import tiquartet.CommonModule.po.RoomPO;
import tiquartet.CommonModule.util.ResultMessage;

public interface RoomDataService {
	public ResultMessage update(RoomPO room);
	public ResultMessage insert(RoomPO room);
	public ResultMessage delete(int roomID);
	public boolean isAvailable(Calendar start, Calendar end, int roomTypeID, int numOfRoom);
	public ResultMessage checkIn (int roomID);
	public ResultMessage checkOut (int roomID);


}
