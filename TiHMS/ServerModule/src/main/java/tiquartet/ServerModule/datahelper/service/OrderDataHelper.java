package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.OrderPO;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;

public interface OrderDataHelper {

	/**
	 * 新增订单
	 * @param 
	 */
	public ResultMessage insert(OrderPO order);
	
	/**
	 * 更新订单
	 * @param 
	 */
	public ResultMessage update(OrderPO order);
	
	/**
	 * 曾经预定的订单
	 * @param 
	 */
	public List<OrderPO> hasBeenOrdered (int hotelID, int userID);
	
	/**
	 * 按id搜索订单
	 * @param 
	 */
	public Map<Long,OrderPO> getOrder ();
	
	/**
	 * 计算订单总数
	 * @param 
	 */
	public int countOrder (int userID, OrderStatus status);

}
