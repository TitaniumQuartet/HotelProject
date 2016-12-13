package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.naming.spi.DirStateFactory.Result;

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
		//实际房间列表
		List<RoomVO> roomVOs = room.getRoomList(000101032);
		//预期房间列表
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
		RoomTypePO roomTypePO = new RoomTypePO(3, "标准间", "两张床的双人间", 180, 000101032);
		RoomTypeVO roomTypeVO = roomTypePO.toRoomTypevo();
		ResultMessage result = room.addRoomType(roomTypeVO);
		assertEquals(new ResultMessage(true), result);
	}
	
	@Test
	public void modifyRoomType() throws RemoteException{
		room = new ManageRoom();
		RoomTypePO roomTypePO = new RoomTypePO(3, "单人间", "一张单人床的房间", 150, 000101032);
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