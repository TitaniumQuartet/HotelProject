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
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.DistrictVO;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.LocationDataImpl;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataService;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.OrderPO;


public class SearchHotel implements SearchHotelBLService {
	
	HotelInfoDataService hotelInfoDataService;
	LocationDataService locationDataService;
	OrderDataService orderDataService;
	
	public SearchHotel(){
		hotelInfoDataService = HotelInfoDataImpl.getInstance();
		locationDataService = LocationDataImpl.getInstance();
		orderDataService = OrderDataImpl.getInstance();
	}
	
	/*
	 * 获取推荐酒店列表   
	 */
	public List<HotelBriefVO> recommend (int userID) throws RemoteException {

		//先调用数据层的方法获取用户订单列表
		List<OrderPO> orderPOs = orderDataService.searchByUser(userID);
		
		List<HotelBriefVO> recommend = new ArrayList<HotelBriefVO>();
		
		//找出最近预定的酒店,即找订单编号最大的
		long max = orderPOs.get(0).getorderId();
		int hotelId = orderPOs.get(0).gethotelId();
		
		for(int i = 0; i < orderPOs.size(); i++){
			if(max < orderPOs.get(i).getorderId()){
				max = orderPOs.get(i).getorderId();
				hotelId = orderPOs.get(i).gethotelId();
			}
		}
		HotelInfoPO hotelInfoPO = hotelInfoDataService.getHotelInfo(hotelId);
		
		HotelBriefVO hotelBriefVO;
		hotelBriefVO = hotelInfoPO.getBriefVO();
		recommend.add(hotelBriefVO);
		
		//找出预定最多的
		long most = orderPOs.get(0).getorderId();
		int hotelId2 = orderPOs.get(0).gethotelId();
		int c1 = 0;
		int c2 = 0;
		int mostIndex = 0;
		
		for(int i = 0; i < orderPOs.size(); i++){
			for(int j = i+1; j < orderPOs.size(); j++){
				if(orderPOs.get(i).getorderId() == orderPOs.get(j).getorderId()){
					c1++;
				}
				if(c1 > c2){
					c2 = c1;
					most = orderPOs.get(i).getorderId();
					mostIndex = i;
				}
				c1 = 0;
			}
		}
		HotelInfoPO hotelInfoPO2 = hotelInfoDataService.getHotelInfo(hotelId2);
		hotelBriefVO = hotelInfoPO2.getBriefVO();
		recommend.add(hotelBriefVO);
		
		//找出次多的
		//先删除最多的再筛选
		orderPOs.remove(mostIndex);
		long secondMost = orderPOs.get(0).getorderId();
		int hotelId3 = orderPOs.get(0).gethotelId();
		int c3 = 0;
		int c4 = 0;
		
		for(int i = 0; i < orderPOs.size(); i++){
			for(int j = i+1; j <orderPOs.size(); j++){
				if(orderPOs.get(i).getorderId() == orderPOs.get(j).getorderId()){
					c3++;
				}
				if(c3 > c4){
					c4 = c3;
					most = orderPOs.get(i).getorderId();
				}
				c3 = 0;
			}
		}
		HotelInfoPO hotelInfoPO3 = hotelInfoDataService.getHotelInfo(hotelId3);
		hotelBriefVO = hotelInfoPO3.getBriefVO();
		recommend.add(hotelBriefVO);
		
		return recommend;
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
			if((hotelInfoPO.gethotelName().contains(filter.hotelName))
					&& (hotelInfoPO.getaverageGrade() >= filter.lowestGrade && hotelInfoPO.getaverageGrade() <= filter.highestGrade)
					&& (hotelInfoPO.getstar() >= filter.lowestStar && hotelInfoPO.getstar() <= filter.highestStar)
					&& (hotelInfoPO.getlowprice() >= filter.lowestPrice && hotelInfoPO.gethighprice() <= filter.highestPrice)
					&& (hotelInfoPO.getcircleId() == filter.districtID)){
				filterList.add(hotelInfoPO);
			}
		}
		
		//运用排序方法进行排序
		
		//星级升序
		if(sort == HotelSort.星级升序){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return (o1.getstar()-o2.getstar());
				}
				});
		}
		//星级降序
		else if(sort == HotelSort.星级降序){
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
		else if(sort == HotelSort.评分升序){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getaverageGrade()*10)-(int)(o2.getaverageGrade()*10));
				}
				});
		}
		//评分降序
		else if(sort == HotelSort.评分降序){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getaverageGrade()*10)-(int)(o2.getaverageGrade()*10));
				}
				});
			//升序后倒置
			Collections.reverse(filterList);
		}
		//价格升序
		else if(sort == HotelSort.均价升序){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getlowprice())-(int)(o2.getlowprice()));
				}
				});
		}
		//价格降序
		else if(sort == HotelSort.均价降序){
			Collections.sort(filterList, new Comparator<HotelInfoPO>(){
				@Override
				public int compare(HotelInfoPO o1, HotelInfoPO o2) {
				return ((int)(o1.getlowprice())-(int)(o2.getlowprice()));
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
		//判断rank2是否越界
		if(rank2-1 > hotelBriefVOs.size()){
			rank2 = hotelBriefVOs.size()+1;
		}
		for(int i = 0; i < hotelBriefVOs.size(); i++){
			if(i < rank1-1 || i > rank2-1){
				hotelBriefVOs.remove(i);
				hotelBriefVOs.add(i, null);
			}
		}
		return hotelBriefVOs;

	}
	
	/*
	 * 获取商圈信息
	 */
	public DistrictVO getDistricts(){
		
		DistrictPO districtPO = locationDataService.renewDistrict();
		DistrictVO districtVO = districtPO.toDistrictVO();
		
		return districtVO;
	}
	
	/*
	 * 更新商圈信息
	 */
	public ResultMessage updateDistricts(DistrictVO district){
		
		DistrictPO districtPO = new DistrictPO(district);
		ResultMessage result = locationDataService.update(districtPO);
		
		return result;
	}

}
