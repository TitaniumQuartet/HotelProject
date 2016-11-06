package tiquartet.ServerModule.bl.manageroombl;

import java.util.*;
import tiquartet.CommonModule.blservice.manageroomblservice.*;
import tiquartet.CommonModule.vo.*;
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
