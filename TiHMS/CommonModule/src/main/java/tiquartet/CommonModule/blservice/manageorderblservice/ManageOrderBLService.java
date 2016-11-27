package tiquartet.CommonModule.blservice.manageorderblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import tiquartet.CommonModule.util.ResultMessage;
import java.util.List;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;;

public interface ManageOrderBLService extends Remote{
	
	public List<OrderVO> orderHistory( OrderFilterVO filter, OrderSort sort, int rank1, int rank2) throws RemoteException;

	public OrderVO getOrderByID (long orderID) throws RemoteException;
	
	public List<OrderVO> hotelOrders(OrderFilterVO filter, OrderSort sort, int rank1, int rank2) throws RemoteException;
		
	public ResultMessage marketerCancel (long orderID, CreditRestore restore) throws RemoteException;
	
	public ResultMessage clientCancel(long orderID) throws RemoteException;
	
	public ResultMessage checkIn(long orderID, String estLeaveTime) throws RemoteException;
	
	public ResultMessage checkOut(long orderID) throws RemoteException;
	
	public List<Integer> orderedHotelID (int userID) throws RemoteException;
	
	public List<OrderVO> clientAtHotel (int userID,int hotelID) throws RemoteException;
	
	public OrderNumVO numAtHotel (int userID,int hotelID) throws RemoteException;
	
}
