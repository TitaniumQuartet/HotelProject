package tiquartet.ServerModule.dataservice.roomdataservice;

import java.util.Calendar;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.RoomPO;

public interface RoomDataService {
	public ResultMessage update(RoomPO room);
	public ResultMessage insert(RoomPO room);
	public ResultMessage delete(int roomID);
	public boolean isAvailable(Calendar start, Calendar end, int roomTypeID, int numOfRoom);
	public ResultMessage checkIn (int roomID);
	public ResultMessage checkOut (int roomID);


}
