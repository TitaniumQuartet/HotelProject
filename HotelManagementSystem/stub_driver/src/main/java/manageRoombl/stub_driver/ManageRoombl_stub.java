package manageRoombl.stub_driver;

import java.util.List;

public class ManageRoombl_stub implements ManageRoombl{

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
