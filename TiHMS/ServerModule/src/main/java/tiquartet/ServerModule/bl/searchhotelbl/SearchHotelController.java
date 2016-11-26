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
	 * 获取商圈信息列表
	 */
	public List<DistrictVO> renewDistrict () {	
		
		return searchhotel.renewDistrict();
	}
	
	/*
	 * 获取网站推荐的酒店
	 */
	public List<HotelVO> recommend (){
		
		return searchhotel.recommand();
	}
	
	/*
	 * 获取酒店列表
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			SortHotelVO sort, int rank1, int rank2){
		
		return searchhotel.getHotelList(filter, sort, rank1, rank2);
	}
}