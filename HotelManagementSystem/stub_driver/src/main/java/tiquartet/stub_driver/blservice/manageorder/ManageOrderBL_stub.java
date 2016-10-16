package tiquartet.stub_driver.blservice.manageorder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageOrderBL_stub {
	
	public List<OrderVO> clientOrderList (char orderState, long userID){
		return new List<OrderVO>();
	}
	
	public List<OrderVO> hotelOrderList (long hotelID, OrderFilterVO orderFilter, int page){
		return new List<OrderVO>();
	}
	
	public ResultMessage clientCancelOrder (String orderID){
		return ResultMessage.succeed;
	}
	
	public ResultMessage marketerCancelOrder (String orderID, CreditRestore restore){
		return ResultMessage.succeed;
	}
	
	public List<OrderVO> clientOrderList (long userID, long hotelID){
		return new List<OrderVO>();
	}
	
	public ResultMessage checkIn(String roomNum, Time leaveTime, String orderID){
		return ResultMessage.succeed;
	}
	
	public ResultMessage checkOut(int orderID){
		return ResultMessage.succeed;
	}
	
	public ArrayList<OrderPO> getOrderList(String city, String area, Date date, int userId, int hotelId, char orderState){
		return new Arraylist<OrderPO>();
	}
	
}
