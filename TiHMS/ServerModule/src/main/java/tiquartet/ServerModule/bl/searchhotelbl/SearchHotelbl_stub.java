package tiquartet.ServerModule.bl.searchhotelbl;

import java.util.*;
import tiquartet.CommonModule.blservice.searchhotelblservice.*;
import tiquartet.CommonModule.vo.*;


public class SearchHotelbl_stub implements SearchHotelBLService{

	public List<DistrictVO> renewDistrict (){
		return new ArrayList<DistrictVO>();
	}
	public List<HotelVO> recommend (){
		return new ArrayList<HotelVO>();
	}
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2){
		return new ArrayList<HotelBriefVO>();
	}
}
