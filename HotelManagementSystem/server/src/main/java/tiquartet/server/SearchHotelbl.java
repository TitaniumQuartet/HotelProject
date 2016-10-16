package tiquartet.server;

import java.util.List;

public interface SearchHotelbl {

	public List<HotelVO> getHotelList (int cityID, int district);
	
	public List<HotelVO> filter (HotelFilterVO filter);
	
}
