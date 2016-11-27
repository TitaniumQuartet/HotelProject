/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;


public class ManageRoom implements ManageRoomBLService {
	
	static DataFactory dataFactory=new DataFactory();
	
	/*
	 * 利用酒店编号获取该酒店的房间列表
	 */
	public List<RoomVO> getRoomList (long hotelID) {
		
		List<RoomPO> list = new ArrayList<RoomPO>();
		list.addAll(dataFactory.getRoomDataHelper().getRoom(hotelID));
		
		//把po转成vo
		List<RoomVO> roomList = new ArrayList<RoomVO>();
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
		
		dataFactory.getRoomDataHelper().update(roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 增加一个房间的信息
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		//vo转po
		RoomPO roompo = new RoomPO();
		BeanUtils.copyPropertites(roompo, room);
		
		dataFactory.getRoomDataHelper().insert(roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 删除一个房间的信息
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		dataFactory.getRoomDataHelper().delete(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 将某个客房的状态设为“入住”
	 */
	public ResultMessage checkIn (int roomID) {
		
		dataFactory.getRoomDataHelper().checkIn(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 将某个房间状态设为“空闲”
	 */
	public ResultMessage checkOut (int roomID) {
		
		dataFactory.getRoomDataHelper().checkOut(roomID);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 修改房间类型
	 */
	public ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		//vo转po
		RoomTypePO roompo = new RoomTypePO();
		BeanUtils.copyPropertites(roompo, roomType);
		
		dataFactory.getRoomDataHelper().updateType(hotelID, roompo);
		
		return new ResultMessage(true);
	}
	
	/*
	 * 删除房间类型
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		dataFactory.getRoomDataHelper().deleteType(hotelID, roomTypeID);
		
		return new ResultMessage(true);
	}
	
}