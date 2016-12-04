package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.OrderPO;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;

public interface OrderDataHelper {

	public OrderPO preOrder(OrderPO preOrder);
	public ResultMessage cancelPreOrder (OrderPO preOrder);
	public ResultMessage insert(OrderPO order);
	public ResultMessage update(OrderPO order);
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	public List<OrderPO> searchByUser (int hotelID, int userID);
	public OrderPO getOrderByID (long orderID);
	public int countOrder (int userID, OrderStatus status);

}
