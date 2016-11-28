package tiquartet.ServerModule.dataservice.impl;

import java.util.List;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataImpl implements OrderDataService{

	public ResultMessage insert(OrderPO order){
		
	}
	
	public ResultMessage update(OrderPO order){
		
	}
	
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	public List<OrderPO> searchByUser (int hotelID, int userID);
	public OrderPO getOrderByID (long orderID);
	public int countOrder (int userID, OrderStatus status);
}
