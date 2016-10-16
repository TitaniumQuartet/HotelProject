package manageHotel.stub_driver;

public class ManageHotelbl_driver {

	public void drive(ManageHotelbl stub){
		
		HotelInfoVO infovo = stub.getHotelInfo();
		System.out.println(infovo);
		
		infovo = stub.modifyHotelInfo();
		System.out.println(infovo);
	}
	
	public static void main(String[] args){
		
		new ManageHotelbl_driver().drive(new ManageHotelbl_stub());
	}
}
