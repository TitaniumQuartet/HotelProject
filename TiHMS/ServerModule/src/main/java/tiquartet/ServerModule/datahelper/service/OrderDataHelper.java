package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.CommonModule.util.OrderStatus;

public interface OrderDataHelper {

	/**
	 * 新增订单
	 * @param 
	 */
	public void insert(OrderPO order);
	
	/**
	 * 更新订单
	 * @param 
	 */
	public void update(OrderPO order);
	
	/**
	 * 曾经预定的订单
	 * @param 
	 */
	public List<OrderPO> hasBeenOrdered (int hotelID, int userID);
	
	/**
	 * 按酒店搜索订单
	 * @param 
	 */
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	
	/**
	 * 按用户搜索订单
	 * @param 
	 */
	public List<OrderPO> searchByUser (int hotelID, int userID);
	
	/**
	 * 按id搜索订单
	 * @param 
	 */
	public OrderPO getOrderByID (long orderID);
	
	/**
	 * 计算订单总数
	 * @param 
	 */
	public int countOrder (int userID, OrderStatus status);

}
