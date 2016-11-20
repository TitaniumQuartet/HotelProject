package tiquartet.ServerModule.dataservice.roomdataservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.*;

public interface RoomDataService {
	
	public ResultMessage preOrder (OrderPO preOrder);
	public ResultMessage cancelPreOrder (OrderPO preOrder);
	public List <RoomTypePO> availableRoomType (int hotelID, String startDate, String endDate, int numOfRoom);
	public ResultMessage update(RoomPO room);
	public ResultMessage insert(RoomPO room);
	public ResultMessage delete(int roomID);
	public ResultMessage checkIn (int roomID);
	public ResultMessage checkOut (int roomID);
	public ResultMessage insertType (int hotelID, RoomTypePO room);
	public ResultMessage updateType (int hotelID, RoomTypePO room);
	
}
