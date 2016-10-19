package tiquartet.StubDriver.blservice.manageorder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tiquartet.ClientModule.blservice.manageorderblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.CreditRestore;

public class ManageOrderBL_stub implements ManageOrderBLService{
	
	public List<OrderVO> clientOrderList (char orderState, long userID){
		return new ArrayList<OrderVO>();
	}
	
	public List<OrderVO> hotelOrderList (long hotelID, OrderFilterVO orderFilter, int page){
		return new ArrayList<OrderVO>();
	}
	
	public ResultMessage clientCancelOrder (String orderID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage marketerCancelOrder (String orderID, CreditRestore restore){
		return ResultMessage.SUCCEED;
	}
	
	public List<OrderVO> clientOrderList (long userID, long hotelID){
		return new ArrayList<OrderVO>();
	}
	
	public ResultMessage checkIn(String roomNum, Time leaveTime, String orderID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage checkOut(int orderID){
		return ResultMessage.SUCCEED;
	}
	
	public List<OrderVO> getOrderList(String city, String area, Date date, int userId, int hotelId, char orderState){
		return new ArrayList<OrderVO>();
	}
	
}
