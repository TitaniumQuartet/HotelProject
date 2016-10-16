package tiquartet.client.blservice.searchhotelblservice;

import java.util.List;
import tiquartet.client.vo.*;

public interface SearchHotelBLService {

	public List<HotelVO> getHotelList (int cityID, int district);
	
	public List<HotelVO> filter (HotelFilterVO filter);
	
}
