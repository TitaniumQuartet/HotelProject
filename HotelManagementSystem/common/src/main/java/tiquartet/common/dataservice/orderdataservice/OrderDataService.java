package tiquartet.common.dataservice.orderdataservice;

public class OrderDataService {
	public List<Order> getHotelList(long userID);
	public ResultMessage insert(OrderPO order);
	public ResultMessage Update(OrderPO order);

}
