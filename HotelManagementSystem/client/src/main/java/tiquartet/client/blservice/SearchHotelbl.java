package tiquartet.client.blservice;

import java.util.List;
import tiquartet.client.vo.*;

public interface SearchHotelbl {

	public List<HotelVO> getHotelList (int cityID, int district);
	
	public List<HotelVO> filter (HotelFilterVO filter);
	
}
