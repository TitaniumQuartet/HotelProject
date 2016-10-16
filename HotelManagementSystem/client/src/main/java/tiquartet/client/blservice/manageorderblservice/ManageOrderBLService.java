package tiquartet.client.blservice.manageorderblservice;

import java.sql.Time;
import tiquartet.common.util.ResultMessage;
import java.util.Date;
import java.util.List;
import tiquartet.client.vo.OrderVO;
import tiquartet.client.vo.OrderFilterVO;
import tiquartet.common.util.CreditRestore;;

public interface ManageOrderBLService {

	public List<OrderVO> clientOrderList (char orderState, long userID);
	
	public List<OrderVO> hotelOrderList (long hotelID, OrderFilterVO orderFilter, int page);
	
	public ResultMessage clientCancelOrder (String orderID);
	
	public ResultMessage marketerCancelOrder (String orderID, CreditRestore restore);
	
	public List<OrderVO> clientOrderList (long userID, long hotelID);
	
	public ResultMessage checkIn(String roomNum, Time leaveTime, String orderID);
	
	public ResultMessage checkOut(int orderID);
	
	public List<OrderVO> getOrderList(String city, String area, Date date, int userId, int hotelId, char orderState);
	
}
