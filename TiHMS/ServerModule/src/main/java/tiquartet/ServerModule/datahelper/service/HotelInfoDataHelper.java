package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.*;
import tiquartet.CommonModule.vo.*;

public interface HotelInfoDataHelper {

	/**
	 * 得到酒店信息
	 * @param 
	 */
	public Map<Integer,HotelInfoPO> getHotelInfo ();
	
	/**
	 * 更新等级
	 * @param 
	 */
	public void updateRate (int HotelID, double newRate);
	
	/**
	 * 新增用户
	 * @param 
	 */
	public void insert (HotelInfoPO hotelInfo);
	
	/**
	 * 更新酒店信息
	 * @param 
	 */
	public void update (HotelInfoPO hotelInfo);
	
	/**
	 * 得到房间类型
	 * @param 
	 */
	public List <RoomTypePO> getRoomTypes (int hotelID);
	
	/**
	 * 获得可用房间
	 * @param 
	 */
	public List <RoomTypePO> availableRoomType (PreOrderVO preOrder);
	
	/**
	 * 获得酒店列表
	 * @param 
	 */
	public List <HotelInfoPO> getHotelList (int districtID);
	
}
