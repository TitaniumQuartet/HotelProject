package tiquartet.stub_driver.blservice.managehotel;

import tiquartet.client.vo.*;
import tiquartet.client.blservice.managehotelblservice.*;

public class ManageHotelBL_driver {

	public void drive(ManageHotelBLService stub){
		
		HotelInfoVO infovo = stub.getHotelInfo(235243);
		System.out.println(infovo.hotelIntroduce);
		
		stub.modifyHotelInfo(infovo);
		System.out.println(infovo.hotelIntroduce);
		System.out.println("Retrieving hotel information...");
	}
	
	public static void main(String[] args){
		
		new ManageHotelBL_driver().drive(new ManageHotelBL_stub());
	}
}
