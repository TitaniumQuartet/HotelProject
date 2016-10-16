package tiquartet.stub_driver.blservice.searchhotel;

import java.util.*;
import tiquartet.client.blservice.searchhotelblservice.*;
import tiquartet.client.vo.*;


public class SearchHotelbl_stub implements SearchHotelBLService{

	public List<HotelVO> getHotelList (int cityID, int district){
		return new ArrayList<HotelVO>();
	}
	
	public List<HotelVO> filter (HotelFilterVO filter){
		return new ArrayList<HotelVO>();
	}
}
