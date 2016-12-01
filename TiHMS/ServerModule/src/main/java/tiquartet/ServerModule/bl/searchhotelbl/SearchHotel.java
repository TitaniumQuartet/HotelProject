/**
 * 搜索酒店的类。
 * 
 * @author Yolanda151250080
 *
 */
package tiquartet.ServerModule.bl.searchhotelbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;
import tiquartet.ServerModule.dataservice.impl.LocationDataImpl;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataService;
import tiquartet.ServerModule.po.HotelPO;

public class SearchHotel implements SearchHotelBLService {
	
	 private LocationDataService locationDataService;
	 
	 public SearchHotel(){
		 locationDataService = LocationDataImpl.getInstance();
	 }
	
	/*
	 * 获取推荐酒店列表
	 */
	public List<HotelBriefVO> recommend (int userID) throws RemoteException {

		//先获取的是一个HotelPO的列表
		List<HotelPO> hotelPOs = locationDataService.getHotelList();
		//将HotelPO列表转成HotelBriefVO的列表
		List<HotelBriefVO> hotelBriefVOs = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelBriefVO;
		for(HotelPO hotelPO: hotelPOs){
			hotelBriefVO = hotelPO.getBriefVO();
			hotelBriefVOs.add(hotelBriefVO);
		}
		
		//下面对酒店进行筛选，这里采用推荐用户预订过的酒店
		List<HotelBriefVO> recommendList = new ArrayList<HotelBriefVO>();
		//获取预定过的酒店编号列表
		ManageOrderController manageOrderController = new ManageOrderController();
		List<Integer> hotelIdList = manageOrderController.orderedHotelID(userID);
		//从列表中筛选出酒店
		for(HotelBriefVO hotelBriefVO2: hotelBriefVOs){
			for(int i = 0; i < hotelIdList.size(); i++){
				if(hotelBriefVO2.hotelID == hotelIdList.get(i)){
					recommendList.add(hotelBriefVO2);
				}
			}			
		}
		
		return recommendList;
	}
	
	/*
	 * 筛选酒店列表
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			HotelSort sort, int rank1, int rank2){
		
		//先获取的是一个HotelPO的列表
		List<HotelPO> hotelPOs = locationDataService.getHotelList();
		//将HotelPO列表进行筛选
		List<HotelPO> filterList = new ArrayList<HotelPO>();
		for(HotelPO hotelPO: hotelPOs){
			if((hotelPO.gethotelName().equals(filter.hotelName))
					&& (hotelPO.getaverage() >= filter.lowestGrade && hotelPO.getaverage() <= filter.highestGrade)
					&& (hotelPO.getstar() == filter.level)
					&& (hotelPO.getlowestPrice() >= filter.lowestPrice && hotelPO.gethighestPrice() <= filter.highestPrice)){
				filterList.add(hotelPO);
			}
		}
		
		//运用排序方法进行排序
		
		//星级升序
		if(sort == HotelSort.STARASCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
		}
		//星级降序
		else if(sort == HotelSort.STARDESCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//先升序再倒置
			Collections.reverse(filterList);
		}
		//评分升序
		else if(sort == HotelSort.RATEASCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getaverage()-o2.getaverage());
				}
				});
		}
		//评分降序
		else if(sort == HotelSort.RATEDESCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//升序后倒置
			Collections.reverse(filterList);
		}
		//价格升序
		else if(sort == HotelSort.PRICEASCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getlowestPrice()-o2.getlowestPrice());
				}
				});
		}
		//价格降序
		else if(sort == HotelSort.PRICEDESCEND){
			Collections.sort(filterList, new Comparator<HotelPO>(){
				@Override
				public int compare(HotelPO o1, HotelPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//升序后倒置
			Collections.reverse(filterList);
		}
		
		//现将筛选排序后的HotelPO列表转成HotelBriefVO列表
		List<HotelBriefVO> hotelBriefVOs = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelBriefVO;
		for(HotelPO hotelPO: filterList){
			hotelBriefVO = hotelPO.getBriefVO();
			hotelBriefVOs.add(hotelBriefVO);
		}
		
		//返回rank1到rank2之间的列表
		return hotelBriefVOs.subList(rank1, rank2);

	}

}
