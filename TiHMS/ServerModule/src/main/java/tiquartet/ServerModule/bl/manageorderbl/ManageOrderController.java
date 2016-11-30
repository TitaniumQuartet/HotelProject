package tiquartet.ServerModule.bl.manageorderbl;

import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;

public class ManageOrderController implements ManageOrderBLService{
	static ManageOrder manageOrder=new ManageOrder();
	
	public List<OrderVO> orderHistory (OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException {
		return manageOrder.orderHistory( filter, sort, rank1, rank2);
	}

	public OrderVO getOrderByID(long orderID)throws RemoteException {
		// TODO Auto-generated method stub
		return manageOrder.getOrderByID(orderID);
	}

	public  List<OrderVO> hotelOrders(OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.hotelOrders(filter, sort, rank1, rank2);
	}

	public  ResultMessage clientCancel(long orderID) throws RemoteException{
		// TODO Auto-generated method stub
		return  manageOrder.clientCancel(orderID);
	}

	public ResultMessage marketerCancel(long orderID, CreditRestore restore) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.marketerCancel(orderID, restore);
	}

	public ResultMessage checkIn(long orderID, String estleaveTime) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.checkIn(orderID, estleaveTime);
	}

	public  ResultMessage checkOut(long orderID,String leaveTime) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.checkOut(orderID,leaveTime);
	}

	public  List<Integer> orderedHotelID(int userID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.orderedHotelID(userID);
	}

	public  List<OrderVO> clientAtHotel(int userID,int hotelID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.clientAtHotel(userID, hotelID);
	}

	public  OrderNumVO numAtHotel(int hotelID,int userID) throws RemoteException{
		// TODO Auto-generated method stub
		return manageOrder.numAtHotel(hotelID, userID);
	}

}
