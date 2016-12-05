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
import tiquartet.ServerModule.bl.hotelinfobl.HotelInfoController;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.LocationDataImpl;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataService;
import tiquartet.ServerModule.po.HotelInfoPO;


public class SearchHotel implements SearchHotelBLService {
	
	HotelInfoDataService hotelInfoDataService;
	
	public SearchHotel(){
		hotelInfoDataService = HotelInfoDataImpl.getInstance();
	}
	
	/*
	 * 获取推荐酒店列表
	 */
	public List<HotelBriefVO> recommend (int userID) throws RemoteException {

		//先调用ManageOrder中的方法获取酒店编号列表
		ManageOrderController manageOrderController = new ManageOrderController();
		List<Integer> hotelIDs = manageOrderController.orderedHotelID(userID);
		
		List<HotelBriefVO> hotelBriefVOs = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelBriefVO;
		
		//调用HotelInfo的getHotelBrief方法
		HotelInfoController hotelInfoController = new HotelInfoController();
		for(int i = 0; i < hotelIDs.size(); i++){
			hotelBriefVO = hotelInfoController.getHotelBrief(hotelIDs.get(i), userID);
			hotelBriefVOs.add(hotelBriefVO);
		}
		
		return hotelBriefVOs;
	}
	
	/*
	 * 筛选酒店列表
	 */
	public List<HotelBriefVO> getHotelList (HotelFilterVO filter, 
			HotelSort sort, int rank1, int rank2){
		
		//先获取的是一个HotelInfoPO的列表
		List<HotelInfoPO> hotelInfoPOs = hotelInfoDataService.getHotelList(filter.districtID);
		
		//将HotelInfoPO列表进行筛选
		List<HotelInfoPO> filterList = new ArrayList<HotelInfoPO>();
		for(HotelInfoPO hotelInfoPO: hotelInfoPOs){
			if((hotelInfoPO.gethotelName().equals(filter.hotelName))
					&& (hotelInfoPO.getaverageGrade() >= filter.lowestGrade && hotelInfoPO.getaverageGrade() <= filter.highestGrade)
					&& (hotelInfoPO.getstar() >= filter.lowestStar && hotelInfoPO.getstar() <= filter.highestStar)
					&& (hotelInfoPO.getlowprice() >= filter.lowestPrice && hotelInfoPO.gethighprice() <= filter.highestPrice)
					&& (hotelInfoPO.getcircleId() == filter.districtID)){
				filterList.add(hotelInfoPO);
			}
		}
		
		//运用排序方法进行排序
		
		//星级升序
		if(sort == HotelSort.STARASCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
		}
		//星级降序
		else if(sort == HotelSort.STARDESCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//先升序再倒置
			Collections.reverse(filterList);
		}
		//评分升序
		else if(sort == HotelSort.RATEASCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getaverageGrade())-(int)(o2.getaverageGrade()));
				}
				});
		}
		//评分降序
		else if(sort == HotelSort.RATEDESCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//升序后倒置
			Collections.reverse(filterList);
		}
		//价格升序
		else if(sort == HotelSort.PRICEASCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getlowprice())-(int)(o2.getlowprice()));
				}
				});
		}
		//价格降序
		else if(sort == HotelSort.PRICEDESCEND){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
			//升序后倒置
			Collections.reverse(filterList);
		}
		
		//现将筛选排序后的HotelPO列表转成HotelBriefVO列表
		List<HotelBriefVO> hotelBriefVOs = new ArrayList<HotelBriefVO>();
		HotelBriefVO hotelBriefVO;
		for(HotelInfoPO hotelInfoPO: filterList){
			hotelBriefVO = hotelInfoPO.getBriefVO();
			hotelBriefVOs.add(hotelBriefVO);
		}
		
		//返回rank1到rank2之间的列表
		return hotelBriefVOs.subList(rank1, rank2+1);

	}

}
