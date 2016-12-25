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
	
	/**
	 * 向数据库中插入一条新的订单记录.
	 * @return
	 */
	public ResultMessage insert(OrderPO order){
		return orderDataHelper.insert(order);
	}
	
	/**
	 * 更新order数据库中的一条记录.
	 * @return
	 */
	public ResultMessage update(OrderPO order){
		return orderDataHelper.update(order);
	}
	
	/**
	 * 根据hotelID和订单状态搜索该酒店的订单.
	 * @return
	 */
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status){
		return orderDataHelper.searchByHotel(hotelID, status);
	}
	
	/**
	 * 根据userID搜索订单.
	 * @return
	 */
	public List<OrderPO> searchByUser (int userID){
		return orderDataHelper.searchByUser(userID);
	}
	
	/**
	 * 根据订单号得到订单信息.
	 * @return
	 */
	public OrderPO getOrderByID (long orderID){
		return orderDataHelper.getOrderByID(orderID);
	}
	
	/**
	 * 计算该用户某种状态的订单总数.
	 * @return
	 */
	public int countOrder (int userID, OrderStatus status){
		return orderDataHelper.countOrder(userID, status);
	}

	/**
	 * 初始的订单生成订单号.
	 * @return
	 */
	@Override
	public OrderPO preOrder(OrderPO preOrder) {
		return orderDataHelper.preOrder(preOrder);
	}

	/**
	 * 取消初始订单.
	 * @return
	 */
	@Override
	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		return orderDataHelper.cancelPreOrder(preOrder);
	}

	/**
	 * 判断酒店未执行订单是否过时，若是，则置为异常订单，并扣除信用值.
	 * @return
	 */
	@Override
	public ResultMessage updateState() {
		return orderDataHelper.updateState();
	}
}
