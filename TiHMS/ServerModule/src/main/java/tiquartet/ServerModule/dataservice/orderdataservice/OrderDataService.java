package tiquartet.ServerModule.dataservice.orderdataservice;

import java.util.List;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.OrderFilterPO;
import tiquartet.ServerModule.po.OrderPO;

public interface OrderDataService {
	
	public ResultMessage insert(OrderPO order);
	public ResultMessage update(OrderPO order);
	public List<OrderPO> hasBeenOrdered (int hotelID, int userID);
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	public List<OrderPO> searchByUser (int hotelID, int userID);
	public OrderPO getOrderByID (long orderID);
	public int countOrder (int userID, OrderStatus status);

}
