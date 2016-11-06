package tiquartet.ServerModule.bl.manageorderbl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tiquartet.CommonModule.blservice.manageorderblservice.*;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;

public class ManageOrderBL_stub implements ManageOrderBLService{
	
	public List<OrderVO> clientOrderList (char orderState, long userID){
		return new ArrayList<OrderVO>();
	}
	
	public List<OrderVO> hotelOrders (long hotelID, OrderFilterVO orderFilter, int page){
		return new ArrayList<OrderVO>();
	}
	
	public ResultMessage clientCancel (String orderID){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage marketerCancel (String orderID, CreditRestore restore){
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

	public List<OrderVO> orderHistory(int userID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderVO getOrderByID(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrderVO> hotelOrders(int hotelID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage clientCancel(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage marketerCancel(long orderID, CreditRestore restore) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage checkIn(long orderID, String leaveTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage checkOut(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> getHotelList(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrderVO> clientAtHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderNumVO numAtHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
