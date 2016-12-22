package tiquartet.ServerModule.bl;


import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.ServerModule.bl.searchhotelbl.SearchHotel;
import tiquartet.ServerModule.po.HotelInfoPO;

public class SearchHotelTest {
	
	private SearchHotel hotel;
	
	@Test
	public void testrecommend() throws RemoteException{
		hotel = new SearchHotel();
		
		List<HotelBriefVO> hotelBriefs = hotel.recommend(1);
		
		List<HotelBriefVO> hotelBriefVOs = new ArrayList<HotelBriefVO>();
		
		HotelInfoPO hotelInfoPO = new HotelInfoPO(000101003, "Seven", 4, "pku", "likehome", "wifi", 000101, "no", 400, 400, 4.5, "bj");
		HotelBriefVO hotelBriefVO = hotelInfoPO.getBriefVO();
		hotelBriefVOs.add(hotelBriefVO);
		
		HotelInfoPO hotelInfoPO2 = new HotelInfoPO(000101001, "NumOne", 4, "nju", "best", "wifi", 000101, "xl", 100, 500, 4.4, "nj");
		HotelBriefVO hotelBriefVO2 = hotelInfoPO2.getBriefVO();
		hotelBriefVOs.add(hotelBriefVO2);
		
		HotelInfoPO hotelInfoPO3 = new HotelInfoPO(000201001, "NumTwo", 5, "nju", "verygood", "water", 000201, "xl", 150, 150, 4.7, "nj");
		HotelBriefVO hotelBriefVO3 = hotelInfoPO3.getBriefVO();
		hotelBriefVOs.add(hotelBriefVO3);
		
		for(int i = 0; i < hotelBriefs.size(); i++){
			assertEquals(hotelBriefs.get(i).circleName, hotelBriefVOs.get(i).circleName);
			assertEquals(hotelBriefs.get(i).cityName, hotelBriefVOs.get(i).cityName);
			assertEquals(hotelBriefs.get(i).hotelID, hotelBriefVOs.get(i).hotelID);
			assertEquals(hotelBriefs.get(i).hotelName, hotelBriefVOs.get(i).hotelName);
			assertEquals(hotelBriefs.get(i).introduction, hotelBriefVOs.get(i).introduction);
			assertEquals(hotelBriefs.get(i).numOfAllOrder, hotelBriefVOs.get(i).numOfAllOrder);
			assertEquals(hotelBriefs.get(i).numOfExecutedOrder, hotelBriefVOs.get(i).numOfExecutedOrder);
			assertEquals(hotelBriefs.get(i).star, hotelBriefVOs.get(i).star);
			
		}
	}

	@Test
	public void testgetHotelList(){
		hotel = new SearchHotel();
		HotelFilterVO hotelFilterVO = new HotelFilterVO();
		hotelFilterVO.cityID = 0002;
		hotelFilterVO.districtID = 201;
		hotelFilterVO.highestPrice = 200;
		hotelFilterVO.lowestPrice = 100;
		hotelFilterVO.lowestStar=1;
		hotelFilterVO.highestStar=5;
		hotelFilterVO.lowestGrade=0;
		hotelFilterVO.highestGrade=5;
		List<HotelBriefVO> hotelBriefVOs = hotel.getHotelList(hotelFilterVO, HotelSort.星级升序, 1, 10);
		
		HotelBriefVO hotelBriefVO = new HotelBriefVO();
		hotelBriefVO.averageGrade = 4.7;
		hotelBriefVO.circleName = "xl";
		hotelBriefVO.cityName = "nj";
		hotelBriefVO.hotelID = 000201001;
		hotelBriefVO.hotelName = "NumTwo";
		hotelBriefVO.introduction = "verygood";
		
		List<HotelBriefVO> hotelBriefVOs2 = new ArrayList<HotelBriefVO>();
		hotelBriefVOs2.add(hotelBriefVO);
		System.out.println(hotelBriefVOs.size());
		for(int i = 0; i < hotelBriefVOs.size(); i++){
			assertEquals(hotelBriefVOs.get(i).circleName, hotelBriefVOs2.get(i).circleName);
			assertEquals(hotelBriefVOs.get(i).cityName, hotelBriefVOs2.get(i).cityName);
			assertEquals(hotelBriefVOs.get(i).hotelID, hotelBriefVOs2.get(i).hotelID);
			assertEquals(hotelBriefVOs.get(i).hotelName, hotelBriefVOs2.get(i).hotelName);
			assertEquals(hotelBriefVOs.get(i).introduction, hotelBriefVOs2.get(i).introduction);
		}	
	}

	@Test
	public void testgetDistricts(){
		hotel = new SearchHotel();
		
	}
	
	@Test
	public void testupdateDistricts(){
		hotel = new SearchHotel();
	}

}
