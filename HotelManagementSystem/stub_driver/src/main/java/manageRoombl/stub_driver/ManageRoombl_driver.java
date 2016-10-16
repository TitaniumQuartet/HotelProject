package manageRoombl.stub_driver;

public class ManageRoombl_driver {

	public void drive(ManageRoombl stub){
		RoomVO roomvo = stub.getRoomList();
		System.out.println(roomvo);
		
		roomvo = stub.modifyRoomStatus();
		System.out.println(roomvo);
		
		roomvo = stub.addRoom();
		System.out.println(roomvo);
		
		roomvo = stub.deleteRoom();
		System.out.println(roomvo);
		
	}
	
	public static void main(String[] args){
		
		new ManageRoombl_driver().drive(new ManageRoombl_stub());
	}
}
