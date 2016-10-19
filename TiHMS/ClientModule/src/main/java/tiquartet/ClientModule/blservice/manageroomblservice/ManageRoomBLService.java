package tiquartet.ClientModule.blservice.manageroomblservice;

import tiquartet.ClientModule.vo.RoomVO;
import tiquartet.CommonModule.util.ResultMessage;
import java.util.List;

public interface ManageRoomBLService {

	public List<RoomVO> getRoomList (long hotelID); 
	
	public ResultMessage modifyRoomStatus (RoomVO room);
	
	public ResultMessage addRoom (RoomVO room);
	
	public ResultMessage deleteRoom (int roomID); 
	
}
