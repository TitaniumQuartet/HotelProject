package tiquartet.ClientModule.ui.searchhotelui;

import java.util.List;

import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.searchhotelbl.*;

public class SearchHotelbl_driver {

public void drive(SearchHotelBLService stub){
		
		List<HotelBriefVO> searchResult = stub.getHotelList(new HotelFilterVO(), new SortHotelVO(), 1, 20);
		System.out.println("Retrieving hotel list...");
		
		List<HotelVO> recommended = stub.recommend();
		System.out.println("Retrieving hotel recommendation");
	}
	
	public static void main(String[] args){
		
		new SearchHotelbl_driver().drive(new SearchHotelbl_stub());
	}
}
