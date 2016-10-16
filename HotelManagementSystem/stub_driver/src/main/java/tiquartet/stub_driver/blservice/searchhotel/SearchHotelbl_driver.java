package tiquartet.stub_driver.blservice.searchhotel;

import java.util.List;

import tiquartet.client.blservice.searchhotelblservice.*;
import tiquartet.client.vo.*;

public class SearchHotelbl_driver {

public void drive(SearchHotelBLService stub){
		
		List<HotelVO> hotelvo = stub.getHotelList(2,34);
		System.out.println("Retrieving hotel list...");
		
		hotelvo = stub.filter(new HotelFilterVO());
		System.out.println("Applying Hotel Filter...");
	}
	
	public static void main(String[] args){
		
		new SearchHotelbl_driver().drive(new SearchHotelbl_stub());
	}
}
