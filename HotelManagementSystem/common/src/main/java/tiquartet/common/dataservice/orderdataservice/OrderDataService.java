package tiquartet.common.dataservice.orderdataservice;

import java.util.List;

import tiquartet.common.po.OrderFilterPO;
import tiquartet.common.po.OrderPO;
import tiquartet.common.util.ResultMessage;

public interface OrderDataService {
	public List<OrderPO> getHotelList(long userID);
	public ResultMessage insert(OrderPO order);
	public ResultMessage update(OrderPO order);
	public boolean hasBeenOrdered(long hotelID, long userID);
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page);

}
