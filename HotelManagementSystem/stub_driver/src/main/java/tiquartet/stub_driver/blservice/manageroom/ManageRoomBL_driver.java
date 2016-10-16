package tiquartet.stub_driver.blservice.manageroom;

import java.util.List;

import tiquartet.client.blservice.manageroomblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;

public class ManageRoomBL_driver {

	public void drive(ManageRoomBLService stub){
		List<RoomVO> roomvo = stub.getRoomList(2423);
		System.out.println("Getting room list");
		ResultMessage message;
		
		message = stub.modifyRoomStatus(new RoomVO());
		System.out.println(message);
		
		message = stub.addRoom(new RoomVO());
		System.out.println(message);
		
		message = stub.deleteRoom(3);
		System.out.println("Deleting room..."+message);
		
	}
	
	public static void main(String[] args){
		
		new ManageRoomBL_driver().drive(new ManageRoomBL_stub());
	}
}
