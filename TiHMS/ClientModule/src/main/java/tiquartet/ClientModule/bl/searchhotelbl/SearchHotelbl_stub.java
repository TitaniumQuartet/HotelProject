package tiquartet.ClientModule.bl.searchhotelbl;

import java.util.*;
import tiquartet.ClientModule.blservice.searchhotelblservice.*;
import tiquartet.ClientModule.vo.*;


public class SearchHotelbl_stub implements SearchHotelBLService{

	public List<HotelVO> getHotelList (int cityID, int district){
		return new ArrayList<HotelVO>();
	}
	
	public List<HotelVO> filter (HotelFilterVO filter){
		return new ArrayList<HotelVO>();
	}
}
