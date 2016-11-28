package tiquartet.ServerModule.bl.searchhotelbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.CommonModule.vo.HotelVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.ServerModule.po.HotelPO;

public class SearchHotel implements SearchHotelBLService {
	
	static DataFactory dataFactory=new DataFactory();
	
	/*
	 * ��ȡ��Ȧ��Ϣ
	 */
	public List<DistrictVO> renewDistrict (){	
		
		//�Ȼ�ȡ��Ȧ��Ϣ��po
		List<DistrictPO> district = new ArrayList<DistrictPO>();
		//district.addAll(dataFactory.getLocationDataHelper().renewDistrict());
		
		//poתvo
		List<DistrictVO> districtList = new ArrayList<DistrictVO>();
		DistrictVO districtvo;
		
		for(DistrictPO districtpo: district){
			districtvo = new DistrictVO(null,null);
			//BeanUtils.copyProperties(districtvo, districtpo);
			districtList.add(districtvo);
		}
		
		return districtList;
	}
	
	/*
	 * ����Ƽ��Ƶ��б�
	 */
	public List<HotelVO> recommend (){
		
		//�Ȼ�ȡ�Ƶ��б�
		List<HotelPO> hotel = new ArrayList<HotelPO>();
		//hotel.addAll(dataFactory.getLocationDataHelper().getHotel());
		
		//poתvo
		List<HotelVO> hotelList = new ArrayList<HotelVO>();
		HotelVO hotelvo = null;
		
		for(HotelPO hotelpo: hotel){
			hotelvo = new HotelVO();
			//BeanUtils.copyProperties(hotelvo, hotelpo);
			hotelList.add(hotelvo);
		}
		
		//����һ������ɸѡ�Ƽ��Ƶ꣬����ƽ����80���ϵ�
		List<HotelVO> recommendHotel = new ArrayList<HotelVO>();
		for(HotelVO hotelvos: hotelList){
			if(hotelvo.averagegrade >= 80){
				recommendHotel.add(hotelvos);
			}
		}
		
		//�����Ƽ��Ƶ��б�
		return recommendHotel;
	}
	
	/*
	 * ��þƵ����б�
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			HotelSort sort, int rank1, int rank2){
		
		//�Ȼ�ȡ�Ƶ�����Ϣ
		List<HotelBriefVO> hotelbrief = new ArrayList<HotelBriefVO>();
		//hotelbrief.addAll(dataFactory.getLocationDataHelper().getHotelBrief());
		
		//poתvo
		List<HotelBriefVO> hotelbriefList = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelbriefvo;
		
		for(HotelBriefVO hotelbriefpo: hotelbrief){
			hotelbriefvo = new HotelBriefVO();
			//BeanUtils.copyProperties(hotelbriefvo, hotelbriefpo);
			hotelbriefList.add(hotelbriefvo);
		}
		
		//����ɸѡ����ɸѡ
		//�Ƚ���HotelBreifVO��HotelFilterVO����ı�������ʱֻ�ܿ��ǶԱ��Ǽ�
		List<HotelBriefVO> filterHotel = new ArrayList<HotelBriefVO>();
		for(HotelBriefVO hotelbreifvos: hotelbriefList){
			if(hotelbreifvos.star == filter.level){
				filterHotel.add(hotelbreifvos);
			}
		}
		
		//����ָ��������б�
		return filterHotel.subList(rank1, rank2);
	}

	@Override
	public List<HotelBriefVO> recommend(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
