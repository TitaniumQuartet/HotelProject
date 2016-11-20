package tiquartet.ServerModule.bl.manageorderbl;

import java.util.List;

import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;

public class ManageOrderController {
	static ManageOrder manageOrder=new ManageOrder();
	
	public static List<OrderVO> orderHistory(int userID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		return manageOrder.orderHistory(userID, filter, sort, rank1, rank2);
	}

	public static OrderVO getOrderByID(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<OrderVO> hotelOrders(int hotelID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage clientCancel(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage marketerCancel(long orderID, CreditRestore restore) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage checkIn(long orderID, String leaveTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage checkOut(long orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Long> getHotelList(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<OrderVO> clientAtHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static OrderNumVO numAtHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

}
