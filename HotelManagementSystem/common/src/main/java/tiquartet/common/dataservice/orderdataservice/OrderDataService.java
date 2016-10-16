package tiquartet.common.dataservice.orderdataservice;

import java.util.List;
import tiquartet.common.po.orderpo.OrderPO;
import tiquartet.common.po.orderfilterpo.OrderFilterPO;
import tiquartet.common.util.resultmessage.ResultMessage;

public interface OrderDataService {
	public List<OrderPO> getHotelList(long userID);
	public ResultMessage insert(OrderPO order);
	public ResultMessage Update(OrderPO order);
	public boolean hasBeenOrdered(long hotelID, long userID);
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page);

}
