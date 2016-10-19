package tiquartet.StubDriver.blservice.manageroom;

import java.util.*;
import tiquartet.ClientModule.blservice.manageroomblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;


public class ManageRoomBL_stub implements ManageRoomBLService{

	public List<RoomVO> getRoomList (long hotelID){
		return new ArrayList<RoomVO>();
	}
	
	public ResultMessage modifyRoomStatus (RoomVO room){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage addRoom (RoomVO room){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage deleteRoom (int roomID){
		return ResultMessage.SUCCEED;
	}
}
