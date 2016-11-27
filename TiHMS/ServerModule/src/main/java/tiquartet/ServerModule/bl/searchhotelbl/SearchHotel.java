package tiquartet.ServerModule.bl.searchhotelbl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.CommonModule.vo.HotelVO;
import tiquartet.CommonModule.vo.SortHotelVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.ServerModule.po.HotelBriefPO;
import tiquartet.ServerModule.po.HotelPO;

public class SearchHotel implements SearchHotelBLService {
	
	static DataFactory dataFactory=new DataFactory();
	
	/*
	 * 获取商圈信息
	 */
	public List<DistrictVO> renewDistrict (){	
		
		//先获取商圈信息的po
		List<DistrictPO> district = new ArrayList<DistrictPO>();
		district.addAll(dataFactory.getLocationDataHelper().renewDistrict());
		
		//po转vo
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		DistrictVO districtvo;
		
		for(DistrictPO districtpo: district){
			districtvo = new DistrictVO();
			BeanUtils.copyProperties(districtvo, districtpo);
			districtList.add(districtvo);
		}
		
		return districtList;
	}
	
	/*
	 * 获得推荐酒店列表
	 */
	public List<HotelVO> recommend (){
		
		//先获取酒店列表
		List<HotelPO> hotel = new ArrayList<HotelPO>();
		hotel.addAll(dataFactory.getLocationDataHelper().getHotel());
		
		//po转vo
		List<HotelVO> hotelList = new ArrayList<HotelVO>();
		HotelVO hotelvo;
		
		for(HotelPO hotelpo: hotel){
			hotelvo = new HotelVO();
			BeanUtils.copyProperties(hotelvo, hotelpo);
			hotelList.add(hotelvo);
		}
		
		//根据一定条件筛选推荐酒店，比如平均分80以上的
		List<HotelVO> recommendHotel = new ArrayList<HotelVO>();
		for(HotelVO hotelvos: hotelList){
			if(hotelvo.averagegrade >= 80){
				recommendHotel.add(hotelvos);
			}
		}
		
		//返回推荐酒店列表
		return recommendHotel;
	}
	
	/*
	 * 获得酒店简介列表
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			SortHotelVO sort, int rank1, int rank2){
		
		//先获取酒店简介信息
		List<HotelBriefPO> hotelbrief = new ArrayList<HotelBriefPO>();
		hotelbrief.addAll(dataFactory.getLocationDataHelper().getHotelBrief());
		
		//po转vo
		List<HotelBriefVO> hotelbriefList = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelbriefvo;
		
		for(HotelBriefPO hotelbriefpo: hotelbrief){
			hotelbriefvo = new HotelBriefVO();
			BeanUtils.copyProperties(hotelbriefvo, hotelbriefpo);
			hotelbriefList.add(hotelbriefvo);
		}
		
		//根据筛选条件筛选
		//比较了HotelBreifVO和HotelFilterVO里面的变量，暂时只能考虑对比星级
		List<HotelBriefVO> filterHotel = new ArrayList<HotelBriefVO>();
		for(HotelBriefVO hotelbreifvos: hotelbriefList){
			if(hotelbreifvos.star == filter.level){
				filterHotel.add(hotelbreifvos);
			}
		}
		
		//返回指定区间的列表
		return filterHotel.subList(rank1, rank2);
	}
}
