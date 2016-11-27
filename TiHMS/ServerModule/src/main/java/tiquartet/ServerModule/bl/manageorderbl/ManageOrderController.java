package tiquartet.ServerModule.bl.manageorderbl;

import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;

public class ManageOrderController {
	static ManageOrder manageOrder=new ManageOrder();
	
	public static List<OrderVO> orderHistory (OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException {
		return manageOrder.orderHistory( filter, sort, rank1, rank2);
	}

	public static OrderVO getOrderByID(long orderID)throws RemoteException {
		// TODO Auto-generated method stub
		return manageOrder.getOrderByID(orderID);
	}

	public static List<OrderVO> hotelOrders(OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.hotelOrders(filter, sort, rank1, rank2);
	}

	public static ResultMessage clientCancel(long orderID) throws RemoteException{
		// TODO Auto-generated method stub
		return  manageOrder.clientCancel(orderID);
	}

	public static ResultMessage marketerCancel(long orderID, CreditRestore restore) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.marketerCancel(orderID, restore);
	}

	public static ResultMessage checkIn(long orderID, String leaveTime) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.checkIn(orderID, leaveTime);
	}

	public static ResultMessage checkOut(long orderID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.checkOut(orderID);
	}

	public static List<Integer> getHotelList(int userID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.getHotelList(userID);
	}

	public static List<OrderVO> clientAtHotel(int userID,int hotelID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.clientAtHotel(userID, hotelID);
	}

	public static OrderNumVO numAtHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

}
