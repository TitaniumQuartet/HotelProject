package tiquartet.ServerModule.data.orderdata;

import java.util.List;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.OrderPO;

public class OrderData_stub implements OrderDataService{
	
	public ResultMessage insert(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
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
