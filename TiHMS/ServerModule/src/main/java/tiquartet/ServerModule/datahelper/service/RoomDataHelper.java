package tiquartet.ServerModule.datahelper.service;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.*;
import java.util.List;
import java.util.Map;
public interface RoomDataHelper {

	public List <RoomTypePO> availableRoomType (int hotelID, String startDate, String endDate, int numOfRoom);
	public ResultMessage update(RoomPO room);
	public ResultMessage insert(RoomPO room);
	public ResultMessage delete(int roomID);
	public ResultMessage checkIn (int roomID);
	public ResultMessage checkOut (int roomID);
	public ResultMessage insertType(RoomTypePO room);
	public ResultMessage updateType(RoomTypePO room);
	public ResultMessage deleteType(RoomTypePO room);
	public List<RoomPO> getRoomList (int hotelID);

}
