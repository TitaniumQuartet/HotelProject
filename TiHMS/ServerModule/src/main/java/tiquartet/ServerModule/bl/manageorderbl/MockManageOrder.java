package tiquartet.ServerModule.bl.manageorderbl;

import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;

public class MockManageOrder implements ManageOrderBLService{

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
