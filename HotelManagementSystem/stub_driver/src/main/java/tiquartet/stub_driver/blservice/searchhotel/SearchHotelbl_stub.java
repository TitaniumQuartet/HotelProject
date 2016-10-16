package tiquartet.stub_driver.blservice.searchhotel;

import java.util.List;

import tiquartet.client.blservice.HotelFilterVO;
import tiquartet.client.blservice.HotelVO;


public class SearchHotelbl_stub {

	public List<HotelVO> getHotelList (int cityID, int district){
		return new List<HotelVO>();
	}
	
	public List<HotelVO> filter (HotelFilterVO filter){
		return new List<HotelVO>();
	}
}
