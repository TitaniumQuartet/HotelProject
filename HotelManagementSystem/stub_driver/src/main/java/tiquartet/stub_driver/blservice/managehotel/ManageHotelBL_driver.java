package tiquartet.stub_driver.blservice.managehotel;

public class ManageHotelBL_driver {

	public void drive(ManageHotelbl stub){
		
		HotelInfoVO infovo = stub.getHotelInfo();
		System.out.println(infovo.hotelInfo);
		
		infovo = stub.modifyHotelInfo();
		System.out.println(infovo.newInfo);
	}
	
	public static void main(String[] args){
		
		new ManageHotelBL_driver().drive(new ManageHotelBL_stub());
	}
}
