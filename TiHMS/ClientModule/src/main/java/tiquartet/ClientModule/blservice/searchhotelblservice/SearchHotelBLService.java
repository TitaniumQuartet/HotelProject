package tiquartet.ClientModule.blservice.searchhotelblservice;

import java.util.List;
import tiquartet.ClientModule.vo.*;

public interface SearchHotelBLService {

	public List<DistrictVO> renewDistrict ();
	public List<HotelVO> recommend ();
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2);
	
}
