package tiquartet.ServerModule.bl.searchhotelbl;

import java.util.List;
import org.apache.commons.beanutils.BeanUtils; 

import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.CommonModule.vo.SortHotelVO;
import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataController;

public class SearchHotel implements SearchHotelBLService {
	
	/*
	 * 获取商圈信息
	 */
	public List<DistrictVO> renewDistrict (){	
		
		//先获取商圈信息的po
		List<DistrictPO> district = new List<DistrictPO>();
		district = addAll(LocationDataController.renewDistrict());
		
		//po转vo
		List<DistrictVO> districtList = new List<DistrictVO>();
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
		List<HotelPO> hotel = new List<HotelPO>();
		hotel = addAll(LocationDataController.getHotel());
		
		//po转vo
		List<HotelVO> hotelList = new List<HotelVO>();
		HotelVO hotelvo;
		
		for(HotelPO hotelpo: hotel){
			hotelvo = new HotelVO();
			BeanUtils.copyProperties(hotelvo, hotelpo);
			hotelList.add(hotelvo);
		}
		
		//根据一定条件筛选推荐酒店，比如平均分80以上的
		List<HotelVO> recommendHotel = new List<HotelVO>();
		for(HotelVO hotelvo: hotelList){
			if(hotelvo.averagegrade >= 80){
				recommendHotel.add(hotelvo);
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
		List<HotelBriefPO> hotelbrief = new List<HotelBriefPO>();
		hotelbrief = addAll(LocationDataController.getHotelBrief());
		
		//po转vo
		List<HotelBriefVO> hotelbriefList = new List<HotelBriefVO>();
		HotelBriefVO hotelbriefvo;
		
		for(HotelBriefPO hotelbriefpo: hotelbrief){
			hotelbriefvo = new HotelBriefVO();
			BeanUtils.copyProperties(hotelbriefvo, hotelbriefpo);
			hotelbriefList.add(hotelbriefvo);
		}
		
		//根据筛选条件筛选
		//比较了HotelBreifVO和HotelFilterVO里面的变量，暂时只能考虑对比星级
		List<HotelBreifVO> filterHotel = new List<HotelBriefVO>();
		for(HotelBreifVO hotelbreifvos: hotelbriefList){
			if(hotelbreifvos.star == filter.level){
				filterHotel.add(hotelbreifvos);
			}
		}
		
		//返回指定区间的列表
		return filterHotel.subList(rank1, rank2);
	}
}
