package tiquartet.ClientModule.bl.searchhotelbl;

import java.util.ArrayList;

import tiquartet.ClientModule.bl.usermainbl.MockUserMain;
import tiquartet.ClientModule.bl.hotelinfobl.MockHotelInfo;
import java.util.List;

import tiquartet.ClientModule.vo.*;

public class SearchHotel {
	
	SearchHotel(){
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
		MockHotelInfo hotel=new MockHotelInfo();
		PreOrderVO preOrder=new PreOrderVO();
		hotel.availableRoomType (preOrder);
		ArrayList<HotelBriefVO> hotelbrief=new ArrayList<HotelBriefVO>();
		return hotelbrief;
	}

}
