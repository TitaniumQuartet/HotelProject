package tiquartet.ClientModule.ui.searchhotelui;

import java.util.List;

import tiquartet.ClientModule.blservice.searchhotelblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.bl.searchhotelbl.*;

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
