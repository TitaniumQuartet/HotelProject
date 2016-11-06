package tiquartet.ClientModule.bl.searchhotelbl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import tiquartet.ClientModule.vo.DistrictVO;
import tiquartet.ClientModule.vo.HotelBriefVO;
import tiquartet.ClientModule.vo.HotelVO;
import tiquartet.ClientModule.vo.RoomTypeVO;

public class SearchHotelTest {
	
	private SearchHotel hotelList;
	
	@Before
	public void setUp(){
		
	}	

	@Test
	public void testrenewDistrict() {
       hotelList=new SearchHotel();
       DistrictVO district1=new DistrictVO();
	   district1.districtID=00001;
	   DistrictVO district2=new DistrictVO();
	   district1.districtID=00002;
       List<DistrictVO> district=hotelList.renewDistrict();
       district.add(district1);
	   district.add(district2);
       assertEquals(district.get(0).districtID,00001);
	}
	
	@Test
	public void testrecommend(){
		hotelList=new SearchHotel();
	    HotelVO hotel1=new HotelVO();
		hotel1.hotelID=0000000001;
		HotelVO hotel2=new HotelVO();
		hotel2.hotelID=0000000002;
	    List<HotelVO> hotel=hotelList.recommend();
	    hotel.add(hotel1);
		hotel.add(hotel2);
	    assertEquals(hotel.get(0).hotelID,0000000001);
	}
	
	@Test
	public void testgetHotelList(){
		ArrayList<HotelBriefVO> hotelbrief=new ArrayList<HotelBriefVO>();
		HotelBriefVO hotel1=new HotelBriefVO();
		HotelBriefVO hotel2=new HotelBriefVO();
		hotel1.hotelID=0000000011;
		hotel2.hotelID=0000000012;
		hotelbrief.add(hotel1);
		hotelbrief.add(hotel2);
		assertEquals(hotelbrief.get(0).hotelID,0000000011);
		assertEquals(hotelbrief.get(1).hotelID,0000000012);
	}

}
