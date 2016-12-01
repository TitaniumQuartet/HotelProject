/**
 * 搜索酒店的controller类。
 * 
 * @author Yolanda151250080
 * 
 */
package tiquartet.ServerModule.bl.searchhotelbl;

import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;

public class SearchHotelController implements SearchHotelBLService{
	
	static SearchHotel searchhotel = new SearchHotel();
	
	/*
	 * 获取推荐酒店列表
	 */
	public List<HotelBriefVO> recommend (int userID) throws RemoteException{
		
		return searchhotel.recommend(userID);
	}
	
	/*
	 * 筛选酒店
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			HotelSort sort, int rank1, int rank2){
		
		return searchhotel.getHotelList(filter, sort, rank1, rank2);
	}
}