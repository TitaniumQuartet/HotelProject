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
		return new ResultMessage(true);
	}
	
	public ResultMessage addRoom(RoomVO room){
		return new ResultMessage(true);
	}
	
	public ResultMessage deleteRoom(int roomID){
		return new ResultMessage(true);
	}
	
	public ResultMessage checkIn(int roomID){
		return new ResultMessage(true);
	}
	
	public ResultMessage checkOut(int roomID){
		return new ResultMessage(true);
	}
	
	public ResultMessage addRoomType(RoomTypeVO roomType){
		return new ResultMessage(true);
	}
	
	public ResultMessage modifyRoomType(RoomTypeVO roomType){
		return new ResultMessage(true);
	}
	
	public ResultMessage deleteRoomType(int hotelID, int roomTypeID){
		return new ResultMessage(true);
	}
	
}