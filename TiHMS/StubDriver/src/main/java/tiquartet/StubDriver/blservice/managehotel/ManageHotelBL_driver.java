package tiquartet.StubDriver.blservice.managehotel;

import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.blservice.managehotelblservice.*;

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
