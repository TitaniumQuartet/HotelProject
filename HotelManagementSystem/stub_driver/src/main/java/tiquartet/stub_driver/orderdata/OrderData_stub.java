package tiquartet.stub_driver.orderdata;

import tiquartet.common.dataservice.orderdataservice;
import java.util.List;

public class OrderData_stub implements OrderDataService{
	
	public List<Order> getHotelList(long userID){
		System.out.println("yes");
		return new List<Order>();
	}
	public ResultMessage insert(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage Update(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}	
	public boolean hasBeenOrdered(long hotelID, long userID){
		System.out.println("yes");
		return true;
	}
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page){
		System.out.println("yes");
		return new List<OrderPO>();
	}

}
