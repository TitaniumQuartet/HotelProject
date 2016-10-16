package tiquartet.stub_driver.blservice.manageroom;

import java.util.*;
import tiquartet.client.blservice.manageroomblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;


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
