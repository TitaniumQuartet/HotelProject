/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommonModule.vo.RoomTypeVO;


public class ManageRoomController {
	
	static ManageRoom manageroom = new ManageRoom();
	
	/*
	 * 利用酒店编号获取酒店房间列表
	 */
	public static List<RoomVO> getRoomList (long hotelID) {
		
		return manageroom.getRoomList(hotelID);
	}
	
	/*
	 * 修改酒店相应房间的信息
	 */
	public static ResultMessage modifyRoomInfo (RoomVO room) {
		
		return manageroom.modifyRoomInfo(room);
	}
	
	/*
	 * 添加相应房间信息
	 */
	public static ResultMessage addRoom (RoomVO room) {
		
		return manageroom.addRoom(room);
	}
	
	/*
	 * 通过房间编号删除酒店某一房间信息
	 */
	public static ResultMessage deleteRoom (int roomID) {
		
		return manageroom.deleteRoom(roomID);
	}
	
	/*
	 * 通过酒店房间编号办理入住手续,即该客房状态设为“入住”
	 */
	public static ResultMessage checkIn (int roomID) {
		
		return manageroom.checkIn(roomID);
	}
	
	/*
	 * 通过酒店房间编号办理退房手续，即该客房状态设为“空闲”
	 */
	public static ResultMessage checkOut (int roomID) {
		
		return manageroom.checkOut(roomID);
	}
	
	/*
	 * 修改房间类型
	 */
	public static ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		return manageroom.modifyRoomType(hotelID, roomType);
	}
	
	/*
	 * 删除房间类型
	 */
	public static ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		return manageroom.deleteRoomType(hotelID, roomTypeID);
	}

}