package tiquartet.ServerModule.datahelper.service;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.*;
import java.util.List;
import java.util.Map;
public interface RoomDataHelper {

	/**
	 * 订过的订单
	 * @param 
	 */
	public ResultMessage preOrder (OrderPO preOrder);
	
	/**
	 * 取消订单
	 * @param 
	 */
	public ResultMessage cancelPreOrder (OrderPO preOrder);
	
	
	public Map<Integer, RoomPO> getRoom ();
	/**
	 * 获得房间类型
	 * @param 
	 */
	public List<RoomTypePO> availableRoomType (int hotelID, String startDate, String endDate, int numOfRoom);
	
	/**
	 * 更新可用房间
	 * @param 
	 */
	public ResultMessage update(RoomPO room);
	
	/**
	 * 新增用户
	 * @param 
	 */
	public ResultMessage insert(RoomPO room);
	
	/**
	 * 删除可用客房
	 * @param 
	 */
	public ResultMessage delete(int roomID);
	
	/**
	 * 客户入住
	 * @param 
	 */
	public ResultMessage checkIn (int roomID);
	
	/**
	 * 客户退房
	 * @param 
	 */
	public ResultMessage checkOut (int roomID);
	
	/**
	 * 新增房间类型
	 * @param 
	 */
	public ResultMessage insertType (int hotelID, RoomTypePO room);
	
	/**
	 * 更新房间类型
	 * @param 
	 */
	public ResultMessage updateType (int hotelID, RoomTypePO room);

}
