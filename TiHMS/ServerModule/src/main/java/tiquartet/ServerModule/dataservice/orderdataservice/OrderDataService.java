package tiquartet.ServerModule.dataservice.orderdataservice;

import java.util.List;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.OrderPO;

public interface OrderDataService {
	
	public OrderPO preOrder (OrderPO preOrder);
	public ResultMessage cancelPreOrder (OrderPO preOrder);
	public ResultMessage insert(OrderPO order);
	public ResultMessage update(OrderPO order);
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	public List<OrderPO> searchByUser (int userID);
	public OrderPO getOrderByID (long orderID);
	public int countOrder (int userID, OrderStatus status);
	public ResultMessage updateState(int hotelId);

}
