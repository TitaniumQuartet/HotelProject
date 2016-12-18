package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;


import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.bl.createorderbl.CreateOrder;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrder;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder createorder = new CreateOrder();
	private ManageOrder manageorder = new ManageOrder();
	@Test
	public void testgetStrategyByID() throws RemoteException{
	}
	
	@Test
	public void testpreOrder() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		ResultMessage result = createorder.preOrder(preorder);
		assertEquals(result.result, false);
		preorder.userID = 123456;
		preorder.hotelID = 12034056;
		preorder.clientRealName = "qinliu";
		preorder.hotelName = "nanda";
		preorder.leaveTime = "2016-12-10 12:30:00";
		preorder.numOfRoom = 1;
		preorder.price = 100.1;
		preorder.userName = "qinliu";
		preorder.roomType = 1;
		preorder.startTime = "2016-12-09 12:30:00";
		result =createorder.preOrder(preorder);
		assertEquals(result.result, true);
	}
	@Test
	public void testcancelPreOrder() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 123456;
		preorder.hotelID = 12034056;
		preorder.clientRealName = "qinliu";
		preorder.hotelName = "nanda";
		preorder.leaveTime = "2016-12-10 12:30:00";
		preorder.numOfRoom = 1;
		preorder.price = 100.1;
		preorder.userName = "qinliu";
		preorder.roomType = 1;
		preorder.startTime = "2016-12-09 12:30:00";
		createorder.preOrder(preorder);
		ResultMessage result=createorder.cancelPreOrder(preorder.userID);
		assertEquals(result.result, true);
	}
	
	@Test
	public void testconfirm() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 123456;
		preorder.hotelID = 12034056;
		preorder.clientRealName = "qinliu";
		preorder.hotelName = "nanda";
		preorder.leaveTime = "2016-12-10 12:30:00";
		preorder.numOfRoom = 1;
		preorder.price = 100;
		preorder.userName = "qinliu";
		preorder.roomType = 1;
		preorder.roomTypeName = "单人房";
		preorder.startTime = "2016-12-09 12:30:00";
		ResultMessage result1=createorder.preOrder(preorder);
		OrderInfoVO order=new OrderInfoVO();
		order.orderID=Integer.parseInt(result1.message);
		order.userID=preorder.userID;
		order.hotelID=preorder.hotelID;
		order.kids=1;
		order.guestRealName="qinliu";
		order.numOfGuest=1;
		order.price=100;
		order.latestTime="2016-12-09 19:30:00";
		order.strategyID=0;
		ResultMessage result=createorder.confirm(order);
		assertEquals(result.result, true);
		OrderVO ordervo=manageorder.getOrderByID(order.orderID);
		assertEquals(order.orderID,ordervo.orderId);
		assertEquals(order.guestRealName,ordervo.guestrealName);
		assertEquals(order.hotelID,ordervo.hotelId);
		assertEquals(order.kids,ordervo.child);
		assertEquals(order.latestTime,ordervo.latestTime);
		assertEquals(order.userID,ordervo.userId);
		assertEquals(order.numOfGuest,ordervo.numberOfPeople);
		assertEquals(preorder.clientRealName,ordervo.clientrealName);
		assertEquals(preorder.startTime,ordervo.startTime);
		assertEquals(preorder.leaveTime,ordervo.leaveTime);
		assertEquals(preorder.roomTypeName,ordervo.roomTypeName);
		assertEquals(preorder.userName,ordervo.userName);
		PreOrderVO preorder1=new PreOrderVO();
		preorder1.userID = 123456;
		preorder1.hotelID = 12034056;
		preorder1.clientRealName = "qinliu";
		preorder1.hotelName = "nanda";
		preorder1.leaveTime = "2016-12-31 12:30:00";
		preorder1.numOfRoom = 1;
		preorder1.price = 100;
		preorder1.userName = "qinliu";
		preorder1.roomType = 1;
		preorder1.roomTypeName = "单人房";
		preorder1.startTime = "2016-12-30 12:30:00";
		ResultMessage result2=createorder.preOrder(preorder);
		OrderInfoVO order1=new OrderInfoVO();
		order1.orderID=Integer.parseInt(result2.message);
		order1.userID=preorder.userID;
		order1.hotelID=preorder.hotelID;
		order1.kids=1;
		order1.guestRealName="qinliu";
		order1.numOfGuest=1;
		order1.price=100;
		order1.latestTime="2016-12-30 19:30:00";
		order1.strategyID=0;
		createorder.confirm(order1);
		OrderSort ordersort = OrderSort.入住日期升序;
		OrderFilterVO orderFilter=new OrderFilterVO();
		List<OrderVO> list=manageorder.orderHistory(orderFilter, ordersort, 0,1);
		assertEquals(list.get(0).orderId,order.orderID);
		assertEquals(list.get(1).orderId,order1.orderID);
		orderFilter.hotelID=12034056;
		List<OrderVO> list1=manageorder.hotelOrders(orderFilter, ordersort,0, 1);
		assertEquals(list1.get(0).orderId,order.orderID );
		assertEquals(list1.get(1).orderId,order1.orderID);
		ResultMessage result3=manageorder.checkIn(order.orderID,"2016-12-10 12:30:00");
		assertEquals(result3.result,true);
		ResultMessage result4=manageorder.checkOut(order.orderID,"2016-12-10 11:00:00");
		assertEquals(result4.result,true);
		ResultMessage result5=manageorder.clientCancel(order.orderID);
		assertEquals(result5.result,true);
		List<Integer> hotelList=manageorder.orderedHotelID(order.userID);
		assertEquals((int)hotelList.get(0),order.hotelID);
		assertEquals((int)hotelList.get(1),order1.hotelID);
	}
}