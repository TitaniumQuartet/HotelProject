package tiquartet.stub_driver.orderdata;

import tiquartet.common.dataservice.orderdataservice.List;
import tiquartet.common.dataservice.orderdataservice.Order;
import tiquartet.common.dataservice.orderdataservice.OrderPO;
import tiquartet.common.dataservice.orderdataservice.ResultMessage;

public class OrderData_stub {
	public List<Order> getHotelList(long userID){
		return new List<Order>();
	}
	public ResultMessage insert(OrderPO order){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage Update(OrderPO order){
		return ResultMessage.SUCCEED;
	}

}
