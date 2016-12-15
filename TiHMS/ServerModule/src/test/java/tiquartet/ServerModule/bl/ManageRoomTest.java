package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.bl.manageroombl.ManageRoom;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;

public class ManageRoomTest{
	
	private ManageRoom room;
	
	@Test
	public void testgetRoomList(){
		room = new ManageRoom();
		//ʵ�ʷ����б�
		List<RoomVO> roomVOs = room.getRoomList(000101032);
		//Ԥ�ڷ����б�
		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101032);
		List<RoomVO> rooms = new ArrayList<RoomVO>();
		rooms.add(roomPO.getRoomVO());
		assertEquals(rooms, roomVOs);
	}
	
	@Test
	public void testmodifyRoomInfo(){
		room = new ManageRoom();
		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101032);
		RoomVO roomVO = roomPO.getRoomVO();
		ResultMessage result = room.modifyRoomInfo(roomVO);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void testaddRoom(){
		room = new ManageRoom();
		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101032);
		RoomVO roomVO = roomPO.getRoomVO();
		ResultMessage result = room.addRoom(roomVO);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void testdeleteRoom(){
		room = new ManageRoom();
		ResultMessage result = room.deleteRoom(111);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void testcheckIn(){
		room = new ManageRoom();
		ResultMessage result = room.checkIn(111);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void testcheckOut(){
		room = new ManageRoom();
		ResultMessage result = room.checkOut(111);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void addRoomType() throws RemoteException{
		room = new ManageRoom();
		RoomTypePO roomTypePO = new RoomTypePO(3, "标准单人间", "有一张床的房间", 180, 000101032);
		RoomTypeVO roomTypeVO = roomTypePO.toRoomTypevo();
		ResultMessage result = room.addRoomType(roomTypeVO);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void modifyRoomType() throws RemoteException{
		room = new ManageRoom();
		RoomTypePO roomTypePO = new RoomTypePO(3, "标准双人间", "有两张床的房间，基本设施齐全", 150, 000101032);
		RoomTypeVO roomTypeVO = roomTypePO.toRoomTypevo();
		ResultMessage result = room.modifyRoomType(roomTypeVO);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void deleteRoomType(){
		room = new ManageRoom();
		ResultMessage result = room.deleteRoomType(000101032, 3);
		assertEquals(new ResultMessage(true), result);
	}
}