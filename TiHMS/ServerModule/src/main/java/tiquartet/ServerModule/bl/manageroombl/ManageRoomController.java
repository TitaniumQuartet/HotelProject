/**
 * 管理酒店房间的controller类。
 * 
 * @author Yolanda151250080
 * 
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.List;

import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;


public class ManageRoomController implements ManageRoomBLService{
	
	static ManageRoom manageroom = new ManageRoom();
	
	/*
	 * 获取酒店房间列表
	 */
	public List<RoomVO> getRoomList (int hotelID) {
		
		return manageroom.getRoomList(hotelID);
	}
	
	/*
	 * 修改客房信息
	 */
	public ResultMessage modifyRoomInfo (RoomVO room) {
		
		return manageroom.modifyRoomInfo(room);
	}
	
	/*
	 * 添加客房
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		return manageroom.addRoom(room);
	}
	
	/*
	 * 删除客房
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		return manageroom.deleteRoom(roomID);
	}
	
	/*
	 * 办理入住
	 */
	public ResultMessage checkIn (int roomID) {
		
		return manageroom.checkIn(roomID);
	}
	
	/*
	 * 办理退房
	 */
	public ResultMessage checkOut (int roomID) {
		
		return manageroom.checkOut(roomID);
	}
	
	/*
	 * 添加客房类型
	 */
	public ResultMessage addRoomType (RoomTypeVO roomType) {
		
		return manageroom.addRoomType(roomType);
	}
	
	/*
	 * 修改客房类型
	 */
	public ResultMessage modifyRoomType (RoomTypeVO roomType) {
		
		return manageroom.modifyRoomType(roomType);
	}
	
	/*
	 * 删除客房类型
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		return manageroom.deleteRoomType(hotelID, roomTypeID);
	}

}