package tiquartet.stub_driver.blservice.manageroom;

import java.util.List;

public class ManageRoomBL_stub implements ManageRoombl{

	public List<RoomVO> getRoomList (long hotelID){
		return new List<RoomVO>();
	}
	
	public ResultMessage modifyRoomStatus (RoomVO room){
		return RessultMessage.succeed;
	}
	
	public ResultMessage addRoom (RoomVO room){
		return RessultMessage.succeed;
	}
	
	public ResultMessage deleteRoom (int roomID){
		return RessultMessage.succeed;
	}
}
