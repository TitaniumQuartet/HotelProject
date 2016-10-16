package tiquartet.common.dataservice.orderdataservice;

public interface OrderDataService {
	public List<Order> getHotelList(long userID);
	public ResultMessage insert(OrderPO order);
	public ResultMessage Update(OrderPO order);
	public boolean hasBeenOrdered(long hotelID, long userID);
	public List<OrderPO> getOrderPage (long hotelID, OrderFilterPO orderFilter, int page);

}
