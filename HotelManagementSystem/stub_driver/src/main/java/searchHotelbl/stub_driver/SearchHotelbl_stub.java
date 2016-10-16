package searchHotelbl.stub_driver;

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
