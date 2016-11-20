package tiquartet.ServerModule.datahelper.service;

import tiquartet.ServerModule.po.*;
import java.util.List;
public interface RoomDataHelper {

	/**
	 * 订过的订单
	 * @param 
	 */
	public void preOrder (OrderPO preOrder);
	
	/**
	 * 取消订单
	 * @param 
	 */
	public void cancelPreOrder (OrderPO preOrder);
	
	/**
	 * 获得房间类型
	 * @param 
	 */
	public List<RoomTypePO> availableRoomType (int hotelID, String startDate, String endDate, int numOfRoom);
	
	/**
	 * 更新可用房间
	 * @param 
	 */
	public void update(RoomPO room);
	
	/**
	 * 新增用户
	 * @param 
	 */
	public void insert(RoomPO room);
	
	/**
	 * 删除可用客房
	 * @param 
	 */
	public void delete(int roomID);
	
	/**
	 * 客户入住
	 * @param 
	 */
	public void checkIn (int roomID);
	
	/**
	 * 客户退房
	 * @param 
	 */
	public void checkOut (int roomID);
	
	/**
	 * 新增房间类型
	 * @param 
	 */
	public void insertType (int hotelID, RoomTypePO room);
	
	/**
	 * 更新房间类型
	 * @param 
	 */
	public void updateType (int hotelID, RoomTypePO room);

}
