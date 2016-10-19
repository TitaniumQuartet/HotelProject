package tiquartet.ClientModule.blservice.searchhotelblservice;

import java.util.List;
import tiquartet.ClientModule.vo.*;

public interface SearchHotelBLService {

	public List<HotelVO> getHotelList (int cityID, int district);
	
	public List<HotelVO> filter (HotelFilterVO filter);
	
}
