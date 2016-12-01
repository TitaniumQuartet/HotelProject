/**
 * 管理酒店房间的类。
 * 
 * @author Yolanda151250080
 * 
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.dataservice.impl.RoomDataImpl;
import tiquartet.ServerModule.dataservice.roomdataservice.RoomDataService;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;


public class ManageRoom implements ManageRoomBLService {
	
	private RoomDataService roomDataService;
	
	public ManageRoom(){
		roomDataService = RoomDataImpl.getInstance();
	}
	
	/*
	 * 获取酒店房间列表
	 */
	public List<RoomVO> getRoomList (long hotelID) {
		
		//获取po列表
		List<RoomPO> roomPOs = new ArrayList<RoomPO>();
		roomPOs = roomDataService.getRoom(hotelID);
		
		//po列表转vo列表
		List<RoomVO> roomVOs = new ArrayList<RoomVO>();
		RoomVO roomVO;
		for(RoomPO roomPO: roomPOs){
			roomVO = roomPO.getRoomVO();
			roomVOs.add(roomVO);
		}
		
		return roomVOs;
	}
	
	/*
	 * 修改房间信息
	 */
	public ResultMessage modifyRoomInfo (RoomVO room) {
		
		//vo转po
		RoomPO roomPO = new RoomPO(room);
		ResultMessage result = roomDataService.update(roomPO);
		
		return result;
	}
	
	/*
	 * 添加酒店房间
	 */
	public ResultMessage addRoom (RoomVO room) {
		
		//vo转po
		RoomPO roomPO = new RoomPO(room);
		ResultMessage result = roomDataService.insert(roomPO);
		
		return result;
	}
	
	/*
	 * 删除酒店房间
	 */
	public ResultMessage deleteRoom (int roomID) {
		
		ResultMessage result = roomDataService.delete(roomID);
		
		return result;
	}
	
	/*
	 * 办理入住
	 */
	public ResultMessage checkIn (int roomID) {
		
		ResultMessage result = roomDataService.checkIn(roomID);
		
		return result;
	}
	
	/*
	 * 办理退房
	 */
	public ResultMessage checkOut (int roomID) {
		
		ResultMessage result = roomDataService.checkOut(roomID);
		
		return result;
	}
	
	/*
	 * 添加客房类型
	 */
	public ResultMessage addRoomType (RoomTypeVO roomType) {
		
		//vo转po
		RoomTypePO roomTypePO = new RoomTypePO(roomType);
		ResultMessage result = roomDataService.insertType(roomTypePO);
		
		return result;
	}
	
	/*
	 * 修改客房类型
	 */
	public ResultMessage modifyRoomType (RoomTypeVO roomType) {
		
		//vo转po
		RoomTypePO roomTypePO = new RoomTypePO(roomType);
		ResultMessage result = roomDataService.updateType(roomTypePO);
		
		return result;
	}
	
	/*
	 * 删除房间类型
	 */
	public ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		ResultMessage result = roomDataService.deleteType(hotelID, roomTypeID);
		
		return result;
	}
	
}