/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.List;
import org.apache.commons.beanutils.BeanUtils; 

import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommenModule.vo.RoomTypeVO;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.ServerModule.dataservice.roomdataservice.RoomDataController;
import tiquartet.ServerModule.po.RoomPO;


public class ManageRoom implements ManageRoomBLService {
	
	/*
	 * 利用酒店编号获取该酒店的房间列表
	 */
	public List<RoomVO> getRoomList (long hotelID) {
		
		List<RoomPO> list = new List<RoomPO>();
		list.addAll(RoomDataController.getRoom(hotelID));
		
		//把po转成vo
		List<RoomVO> roomList = new List<RoomVO>();
		RoomVO roomvo;
		
		for(RoomPO roompo: list){
			roomvo = new RoomVO();
			BeanUtils.copyProperties(roomvo, roompo);
			roomList.add(roomvo);
		}
		
		return roomList;
	}
	
	/*
	 * 修改某个房间的信息
	 */
	public ResultMessage modifyRoomInfo (RoomVO room) {
		
		//vo转po
		RoomPO roompo = new RoomPO();
		BeanUtils.copyPropertites(roompo, room);
		
		return RoomDataController.update(roompo) ;
	}
	
	/*
	 * 增加一个房间的信息
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		//vo转po
		RoomPO roompo = new RoomPO();
		BeanUtils.copyPropertites(roompo, room);
		
		return RoomDataController.insert(roompo);
	}
	
	/*
	 * 删除一个房间的信息
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		return RoomDataController.delete(roomID);
	}
	
	/*
	 * 将某个客房的状态设为“入住”
	 */
	public ResultMessage checkIn (int roomID) {
		
		return RoomDataController.checkIn(roomID);
	}
	
	/*
	 * 将某个房间状态设为“空闲”
	 */
	public ResultMessage checkOut (int roomID) {
		
		return RoomDataController.checkOut(roomID);
	}
	
	/*
	 * 修改房间类型
	 */
	public ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		return RoomDataController.updateType(hotelID, roomType);
	}
	
	/*
	 * 删除房间类型
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		return RoomDateController.deleteType(hotelID, roomTypeID);
	}
	
}