package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.*;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.po.OrderPO;

public class OrderDataImpl implements OrderDataService{

	private Map<Long, OrderPO> map;
	
	private OrderDataHelper orderDataHelper;
	
	private DataFactoryService dataFactory;

	private static OrderDataImpl orderDataServiceImpl;
	
	private Connection con;
	
	public static OrderDataImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDataImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDataImpl(){
		if(map == null){
			dataFactory = new DataFactory();
			orderDataHelper = dataFactory.getOrderDataHelper();
			map = orderDataHelper.getOrder();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage insert(OrderPO order){
		return orderDataHelper.insert(order);
	}
	
	public ResultMessage update(OrderPO order){
		long orderId = order.getorderId();
		if(map.get(orderId) != null){
			orderDataHelper.update(order);
			return success;
		}
		return fail;
	}
	
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status){
		List<OrderPO> orderList = new ArrayList<OrderPO>();
		Iterator<Map.Entry<Long,OrderPO>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Long, OrderPO> entry = iterator.next();
			OrderPO orderPo = entry.getValue();
			if(orderPo.gethotelId() == hotelID&&orderPo.getorderStatus() == status){
				orderList.add(orderPo);
			}
		}
		return orderList;
	}
	
	public List<OrderPO> searchByUser (int hotelID, int userID){
		List<OrderPO> orderList = new ArrayList<OrderPO>();
		Iterator<Map.Entry<Long,OrderPO>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Long, OrderPO> entry = iterator.next();
			OrderPO orderPo = entry.getValue();
			if(orderPo.gethotelId() == hotelID && orderPo.getuserId() == userID){
				orderList.add(orderPo);
			}
		}
		return orderList;
	}
	
	public OrderPO getOrderByID (long orderID){
		OrderPO orderpo=map.get(orderID);
		return orderpo;
	}
	
	public int countOrder (int userID, OrderStatus status){
		return 0;
	}
}
