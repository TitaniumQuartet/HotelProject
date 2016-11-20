package tiquartet.ServerModule.dataservice.orderdataservice;

import java.util.List;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataController{
	
	OrderDataHelper helper;

	public static ResultMessage insert(OrderPO order) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage update(OrderPO order) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<OrderPO> hasBeenOrdered(int hotelID, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<OrderPO> searchByHotel(int hotelID, OrderStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<OrderPO> searchByUser(int hotelID, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static OrderPO getOrderByID(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int countOrder(int userID, OrderStatus status) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
