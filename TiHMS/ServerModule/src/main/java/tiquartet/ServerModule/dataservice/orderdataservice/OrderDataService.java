package tiquartet.ServerModule.dataservice.orderdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.OrderFilterPO;
import tiquartet.ServerModule.po.OrderPO;

public interface OrderDataService {
	public List<OrderPO> getHotelList(long userID);
	public ResultMessage insert(OrderPO order);
	public ResultMessage update(OrderPO order);
	public boolean hasBeenOrdered(long hotelID, long userID);
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page);

}
