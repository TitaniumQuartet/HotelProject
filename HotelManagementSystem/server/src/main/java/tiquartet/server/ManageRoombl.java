package tiquartet.server;

import java.util.List;

public interface ManageRoombl {

	public List<RoomVO> getRoomList (long hotelID); 
	
	public ResultMessage modifyRoomStatus (RoomVO room);
	
	public ResultMessage addRoom (RoomVO room);
	
	public ResultMessage deleteRoom (int roomID); 
	
}
