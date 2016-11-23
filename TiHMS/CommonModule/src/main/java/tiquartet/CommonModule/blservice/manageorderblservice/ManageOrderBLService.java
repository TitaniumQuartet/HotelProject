package tiquartet.CommonModule.blservice.manageorderblservice;

import java.sql.Time;
import tiquartet.CommonModule.util.ResultMessage;
import java.util.Date;
import java.util.List;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;;

public interface ManageOrderBLService {
	
	public List<OrderVO> orderHistory(int userID, OrderFilterVO filter, OrderSort sort, int rank1, int rank2);

	public OrderVO getOrderByID (long orderID);
	
	public List<OrderVO> hotelOrders(int hotelID, OrderFilterVO filter, OrderSort sort, int rank1, int rank2);
	
	public ResultMessage clientCancel (long orderID);
	
	public ResultMessage marketerCancel (long orderID, CreditRestore restore);
	
	public ResultMessage checkIn(long orderID, String leaveTime);
	
	public ResultMessage checkOut(long orderID);
	
	public List<Integer> getHotelList (int userID);
	
	public List<OrderVO> clientAtHotel (int userID,int hotelID);
	
	public OrderNumVO numAtHotel (int hotelID);
	
}
