package tiquartet.ServerModule.bl;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.bl.manageroombl.ManageRoom;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class ManageRoomTest{
	
	private ManageRoom room;
	
	@Test
	public void testgetRoomList(){
		room = new ManageRoom();
		
		List<RoomVO> roomVOs = room.getRoomList(000101001);

		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101001);
		List<RoomVO> rooms = new ArrayList<RoomVO>();
		rooms.add(roomPO.getRoomVO());
		for(int i = 0; i < roomVOs.size(); i++){
			assertEquals(rooms.get(i).hotelId, roomVOs.get(i).hotelId);
			assertEquals(rooms.get(i).roomID, roomVOs.get(i).roomID);
			assertEquals(rooms.get(i).roomNum, roomVOs.get(i).roomNum);
			assertEquals(rooms.get(i).roomStatus, roomVOs.get(i).roomStatus);
			assertEquals(rooms.get(i).roomType, roomVOs.get(i).roomType);
		}
	}
	
	@Test
	public void testmodifyRoomInfo(){
		room = new ManageRoom();
		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101001);
		RoomVO roomVO = roomPO.getRoomVO();
		ResultMessage resultMessage = room.modifyRoomInfo(roomVO);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void testaddRoom(){
		room = new ManageRoom();
		RoomPO roomPO = new RoomPO(111, "413", 3, RoomStatus.空闲, 000101001);
		RoomVO roomVO = roomPO.getRoomVO();
		ResultMessage resultMessage = room.addRoom(roomVO);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void testdeleteRoom(){
		room = new ManageRoom();
		ResultMessage resultMessage = room.deleteRoom(111);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void testcheckIn(){
		room = new ManageRoom();
		ResultMessage resultMessage = room.checkIn(111);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void testcheckOut(){
		room = new ManageRoom();
		ResultMessage resultMessage = room.checkOut(111);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void addRoomType() throws RemoteException{
		room = new ManageRoom();

		RoomTypePO roomTypePO = new RoomTypePO(3, "双人间", "有两张床的房间", 180, 000101001);

		RoomTypeVO roomTypeVO = roomTypePO.toRoomTypevo();
		ResultMessage resultMessage = room.addRoomType(roomTypeVO);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void modifyRoomType() throws RemoteException{
		room = new ManageRoom();

		RoomTypePO roomTypePO = new RoomTypePO(3, "单人间", "只有一张单人床的房间", 150, 000101001);

		RoomTypeVO roomTypeVO = roomTypePO.toRoomTypevo();
		ResultMessage resultMessage = room.modifyRoomType(roomTypeVO);
		assertEquals(true, resultMessage.result);
	}
	
	@Test
	public void deleteRoomType(){
		room = new ManageRoom();
		ResultMessage resultMessage = room.deleteRoomType(000101001, 3);
		assertEquals(true, resultMessage.result);
	}
}