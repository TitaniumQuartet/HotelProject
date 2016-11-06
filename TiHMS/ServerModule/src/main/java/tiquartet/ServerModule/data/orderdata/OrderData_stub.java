package tiquartet.ServerModule.data.orderdata;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.*;

import java.util.*;

public class OrderData_stub implements OrderDataService{
	
	public List<OrderPO> getHotelList(long userID){
		System.out.println("yes");
		return new ArrayList<OrderPO>();
	}
	public ResultMessage insert(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(OrderPO order){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}	
	public boolean hasBeenOrdered(long hotelID, long userID){
		System.out.println("yes");
		return true;
	}
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page){
		System.out.println("yes");
		return new ArrayList<OrderPO>();
	}

}
