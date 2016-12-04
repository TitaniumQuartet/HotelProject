package tiquartet.ServerModule.dataservice.impl;

import java.util.*;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataImpl implements OrderDataService{
	
	private OrderDataHelper orderDataHelper;
	
	private DataFactoryService dataFactory;

	private static OrderDataImpl orderDataServiceImpl;
	
	public static OrderDataImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDataImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			orderDataHelper = dataFactory.getOrderDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage insert(OrderPO order){
		return orderDataHelper.insert(order);
	}
	
	public ResultMessage update(OrderPO order){
		return orderDataHelper.update(order);
	}
	
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status){
		return orderDataHelper.searchByHotel(hotelID, status);
	}
	
	public List<OrderPO> searchByUser (int hotelID, int userID){
		return orderDataHelper.searchByUser(hotelID, userID);
	}
	
	public OrderPO getOrderByID (long orderID){
		return orderDataHelper.getOrderByID(orderID);
	}
	
	public int countOrder (int userID, OrderStatus status){
		return orderDataHelper.countOrder(userID, status);
	}

	@Override
	public OrderPO preOrder(OrderPO preOrder) {
		return orderDataHelper.preOrder(preOrder);
	}

	@Override
	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		return orderDataHelper.cancelPreOrder(preOrder);
	}
}
