package tiquartet.ServerModule.datahelper;

import java.util.List;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataSqlHelper implements OrderDataHelper{

	public void insert(OrderPO order) {
		// TODO Auto-generated method stub
		
	}

	public void update(OrderPO order) {
		// TODO Auto-generated method stub
		
	}

	public List<OrderPO> hasBeenOrdered(int hotelID, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrderPO> searchByHotel(int hotelID, OrderStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrderPO> searchByUser(int hotelID, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderPO getOrderByID(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int countOrder(int userID, OrderStatus status) {
		// TODO Auto-generated method stub
		return 0;
	}

}
