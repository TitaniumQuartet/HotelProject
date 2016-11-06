package tiquartet.CommonModule.blservice.searchhotelblservice;

import java.util.List;

import tiquartet.CommonModule.vo.*;

public interface SearchHotelBLService {

	public List<DistrictVO> renewDistrict ();
	public List<HotelVO> recommend ();
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2);
	
}
