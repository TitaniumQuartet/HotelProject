package tiquartet.client.blservice.manageroomblservice;

import tiquartet.client.vo.*;

public interface ManageRoomBLService {

	public List<RoomVO> getRoomList (long hotelID); 
	
	public ResultMessage modifyRoomStatus (RoomVO room);
	
	public ResultMessage addRoom (RoomVO room);
	
	public ResultMessage deleteRoom (int roomID); 
	
}
