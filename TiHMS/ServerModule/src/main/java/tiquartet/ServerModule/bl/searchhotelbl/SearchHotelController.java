package tiquartet.ServerModule.bl.searchhotelbl;

import java.util.List;

import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.CommonModule.vo.SortHotelVO;

public class SearchHotelController {
	
	static SearchHotel searchhotel = new SearchHotel();
	
	/*
	 * ��ȡ��Ȧ��Ϣ�б�
	 */
	public List<DistrictVO> renewDistrict () {	
		
		return searchhotel.renewDistrict();
	}
	
	/*
	 * ��ȡ��վ�Ƽ��ľƵ�
	 */
	public List<HotelVO> recommend (){
		
		return searchhotel.recommand();
	}
	
	/*
	 * ��ȡ�Ƶ��б�
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			SortHotelVO sort, int rank1, int rank2){
		
		return searchhotel.getHotelList(filter, sort, rank1, rank2);
	}
}