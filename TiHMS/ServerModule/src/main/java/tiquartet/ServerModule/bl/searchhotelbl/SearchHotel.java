package tiquartet.ServerModule.bl.searchhotelbl;

import java.util.ArrayList;

import tiquartet.ServerModule.bl.usermainbl.MockUserMain;
import java.util.List;

import tiquartet.CommonModule.vo.*;

public class SearchHotel {
	
	public SearchHotel(){
		MockUserMain userMain=new MockUserMain();
		userMain.currentUser();
	}
	public List<DistrictVO> renewDistrict (){	
		ArrayList<DistrictVO> districtlist=new ArrayList<DistrictVO>();
		return districtlist;
	}
	public List<HotelVO> recommend (){
		ArrayList<HotelVO> hotellist=new ArrayList<HotelVO>();	
		return hotellist;
	}
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2){
		
		return null;
	}

}
