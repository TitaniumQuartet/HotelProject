package tiquartet.ServerModule.dataservice.impl;

import java.util.List;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataImpl implements OrderDataService{

	public ResultMessage insert(OrderPO order){
		return null;
	}
	
	public ResultMessage update(OrderPO order){
		return null;
	}
	
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status){
		return null;
	}
	public List<OrderPO> searchByUser (int hotelID, int userID){
		return null;
	}
	public OrderPO getOrderByID (long orderID){
		return null;
	}
	public int countOrder (int userID, OrderStatus status){
		return 0;
	}
}
