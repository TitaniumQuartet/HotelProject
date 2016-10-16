package tiquartet.stub_driver.blservice.manageroom;

public class ManageRoomBL_driver {

	public void drive(ManageRoombl stub){
		RoomVO roomvo = stub.getRoomList();
		System.out.println(roomvo.list);
		
		roomvo = stub.modifyRoomStatus();
		System.out.println(roomvo.status);
		
		roomvo = stub.addRoom();
		System.out.println(roomvo.result);
		
		roomvo = stub.deleteRoom();
		System.out.println(roomvo.result);
		
	}
	
	public static void main(String[] args){
		
		new ManageRoomBL_driver().drive(new ManageRoomBL_stub());
	}
}
