package tiquartet.ServerModule.bl.manageroombl;

import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.util.ResultMessage;

public class ManageRoom{
	
	public List<RoomVO> getRoomList(int hotelID){
		return new ArrayList<RoomVO>();
	}
	
	public ResultMessage modifyRoomInfo(RoomVO room){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage addRoom(RoomVO room){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage deleteRoom(int roomID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage checkIn(int roomID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage checkOut(int roomID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage addRoomType(RoomTypeVO roomType){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage modifyRoomType(RoomTypeVO roomType){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage deleteRoomType(int hotelID, int roomTypeID){
		return ResultMessage.SUCCEED;
	}
	
}