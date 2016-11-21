package tiquartet.ServerModule.bl.manageorderbl;

import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.OrderPO;

public class ManageOrder implements ManageOrderBLService{
	static DataFactory dataFactory=new DataFactory();

	public List<OrderVO> orderHistory(int userID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		return null;
	}

	public OrderVO getOrderByID(long orderID) {
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		return new OrderVO();//未完成的返回内容，需要补充po转化为vo的方法。
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
