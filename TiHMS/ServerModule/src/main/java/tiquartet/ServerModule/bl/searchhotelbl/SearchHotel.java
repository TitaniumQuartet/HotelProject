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
	 * ��ȡ��Ȧ��Ϣ
	 */
	public List<DistrictVO> renewDistrict (){	
		
		//�Ȼ�ȡ��Ȧ��Ϣ��po
		List<DistrictPO> district = new List<DistrictPO>();
		district = addAll(LocationDataController.renewDistrict());
		
		//poתvo
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
	 * ����Ƽ��Ƶ��б�
	 */
	public List<HotelVO> recommend (){
		
		//�Ȼ�ȡ�Ƶ��б�
		List<HotelPO> hotel = new List<HotelPO>();
		hotel = addAll(LocationDataController.getHotel());
		
		//poתvo
		List<HotelVO> hotelList = new List<HotelVO>();
		HotelVO hotelvo;
		
		for(HotelPO hotelpo: hotel){
			hotelvo = new HotelVO();
			BeanUtils.copyProperties(hotelvo, hotelpo);
			hotelList.add(hotelvo);
		}
		
		//����һ������ɸѡ�Ƽ��Ƶ꣬����ƽ����80���ϵ�
		List<HotelVO> recommendHotel = new List<HotelVO>();
		for(HotelVO hotelvo: hotelList){
			if(hotelvo.averagegrade >= 80){
				recommendHotel.add(hotelvo);
			}
		}
		
		//�����Ƽ��Ƶ��б�
		return recommendHotel;
	}
	
	/*
	 * ��þƵ����б�
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			SortHotelVO sort, int rank1, int rank2){
		
		//�Ȼ�ȡ�Ƶ�����Ϣ
		List<HotelBriefPO> hotelbrief = new List<HotelBriefPO>();
		hotelbrief = addAll(LocationDataController.getHotelBrief());
		
		//poתvo
		List<HotelBriefVO> hotelbriefList = new List<HotelBriefVO>();
		HotelBriefVO hotelbriefvo;
		
		for(HotelBriefPO hotelbriefpo: hotelbrief){
			hotelbriefvo = new HotelBriefVO();
			BeanUtils.copyProperties(hotelbriefvo, hotelbriefpo);
			hotelbriefList.add(hotelbriefvo);
		}
		
		//����ɸѡ����ɸѡ
		//�Ƚ���HotelBreifVO��HotelFilterVO����ı�������ʱֻ�ܿ��ǶԱ��Ǽ�
		List<HotelBreifVO> filterHotel = new List<HotelBriefVO>();
		for(HotelBreifVO hotelbreifvos: hotelbriefList){
			if(hotelbreifvos.star == filter.level){
				filterHotel.add(hotelbreifvos);
			}
		}
		
		//����ָ��������б�
		return filterHotel.subList(rank1, rank2);
	}
}
