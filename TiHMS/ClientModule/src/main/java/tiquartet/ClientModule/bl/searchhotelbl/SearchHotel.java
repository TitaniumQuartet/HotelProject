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
		return new ArrayList<DistrictVO>();
	}
	public List<HotelVO> recommend (){
		return new ArrayList<HotelVO>();
	}
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, SortHotelVO sort, int rank1, int rank2){
		MockHotelInfo hotel=new MockHotelInfo();
		PreOrderVO preOrder=new PreOrderVO();
		hotel.availableRoomType (preOrder);
		return new ArrayList<HotelBriefVO>();
	}

}
