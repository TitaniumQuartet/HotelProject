package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;

import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.ServerModule.bl.manageroombl.ManageRoom;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.util.ResultMessage;

public class ManageRoomTest{
	
	private ManageRoom room;
	/*
	@Test
	public void testgetRoomList(){
		room = new ManageRoom();
		List<RoomVO> vo = room.getRoomList(12345678);
		assertEquals(vo, vo);
	}
	
	@Test
	public void testmodifyRoomInfo(){
		room = new ManageRoom();
		RoomVO vo = new RoomVO();
		ResultMessage result = room.modifyRoomInfo(vo);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testaddRoom(){
		room = new ManageRoom();
		RoomVO vo = new RoomVO();
		ResultMessage result = room.addRoom(vo);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testdeleteRoom(){
		room = new ManageRoom();
		ResultMessage result = room.deleteRoom(00000001);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testcheckIn(){
		room = new ManageRoom();
		ResultMessage result = room.checkIn(00000001);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
	}
	
	@Test
	public void testcheckOut(){
		room = new ManageRoom();
		ResultMessage result = room.checkOut(00000001);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
	}
	
	@Test
	public void addRoomType() throws RemoteException{
		room = new ManageRoom();
		RoomTypeVO vo = new RoomTypeVO();
		ResultMessage result = room.addRoomType(vo);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
		
	}
	
	@Test
	public void modifyRoomType() throws RemoteException{
		room = new ManageRoom();
		RoomTypeVO vo = new RoomTypeVO();
		ResultMessage result = room.modifyRoomType(vo);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
		
	}
	
	@Test
	public void deleteRoomType(){
		room = new ManageRoom();
		ResultMessage result = room.deleteRoomType(12345678, 11111111);
		result=new ResultMessage(true);
		assertEquals(result.result,true);
		
	}*/
}