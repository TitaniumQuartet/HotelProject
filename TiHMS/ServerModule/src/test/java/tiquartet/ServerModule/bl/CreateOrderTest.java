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
import tiquartet.CommonModule.vo.OrderStrategyVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder createorder = new CreateOrder();
	private ManageOrder manageorder = new ManageOrder();
	@Test
	public void testgetStrategy() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 1;
		preorder.hotelID = 101001;
		preorder.clientRealName="ccc";
		preorder.hotelName = "NumOne";
		preorder.startTime = "2017-01-01 12:00:00";
		preorder.leaveTime = "2017-01-02 12:00:00";
		preorder.roomType = 1;
		preorder.roomTypeName = "";
		preorder.userName = "Teki";
		preorder.price = 300;
		preorder.numOfRoom = 1;
		ResultMessage result1=createorder.preOrder(preorder);
		assertEquals(result1.result,true);
		OrderStrategyVO orderstrategy=createorder.getStrategy(preorder.userID);
		OrderInfoVO orderInfo=new OrderInfoVO();
		orderInfo.strategyID=orderstrategy.strategyID;
		orderInfo.orderID=orderstrategy.orderID;
		orderInfo.price=orderstrategy.orderPrice;
		orderInfo.userID=preorder.userID;
		orderInfo.hotelID=preorder.hotelID;
		orderInfo.numOfGuest=1;
		orderInfo.kids=0;
		orderInfo.guestRealName="lq";
		orderInfo.latestTime="2017-01-01 16:00:00";
		ResultMessage result2=createorder.confirm(orderInfo);
		assertEquals(result2.result,true);
		PreOrderVO preorder1=new PreOrderVO();
		preorder1.userID = 1;
		preorder1.hotelID = 101001;
		preorder1.clientRealName="ccc";
		preorder1.hotelName = "NumOne";
		preorder1.startTime = "2017-01-02 12:00:00";
		preorder1.leaveTime = "2017-01-03 12:00:00";
		preorder1.roomType = 1;
		preorder1.roomTypeName = "";
		preorder1.userName = "Teki";
		preorder1.price = 300;
		preorder1.numOfRoom = 1;
		ResultMessage result3=createorder.preOrder(preorder);
		assertEquals(result3.result,true);
		OrderStrategyVO orderstrategy1=createorder.getStrategy(preorder.userID);
		OrderInfoVO orderInfo1=new OrderInfoVO();
		orderInfo1.strategyID=orderstrategy1.strategyID;
		orderInfo1.orderID=orderstrategy1.orderID;
		orderInfo1.price=orderstrategy1.orderPrice;
		orderInfo1.userID=preorder1.userID;
		orderInfo1.hotelID=preorder1.hotelID;
		orderInfo1.numOfGuest=1;
		orderInfo1.kids=0;
		orderInfo1.guestRealName="lq";
		orderInfo1.latestTime="2017-01-02 16:00:00";
		ResultMessage result4=createorder.confirm(orderInfo);
		assertEquals(result4.result,true);
		OrderSort ordersort = OrderSort.入住日期升序;
		OrderFilterVO orderFilter=new OrderFilterVO();
		List<OrderVO> list=manageorder.orderHistory(orderFilter, ordersort, 0,1);
		assertEquals(list.get(0).orderId,orderInfo.orderID);
		assertEquals(list.get(1).orderId,orderInfo1.orderID);
		orderFilter.hotelID=101001;
		List<OrderVO> list1=manageorder.hotelOrders(orderFilter, ordersort,0, 1);
		assertEquals(list1.get(0).orderId,orderInfo.orderID );
		assertEquals(list1.get(1).orderId,orderInfo1.orderID);
		ResultMessage result5=manageorder.checkIn(orderInfo.orderID,"2016-12-10 12:30:00");
		assertEquals(result5.result,true);
		ResultMessage result6=manageorder.checkOut(orderInfo.orderID,"2016-12-10 11:00:00");
		assertEquals(result6.result,true);
		List<Integer> hotelList=manageorder.orderedHotelID(orderInfo.userID);
		assertEquals((int)hotelList.get(0),orderInfo.hotelID);
		assertEquals((int)hotelList.get(1),orderInfo1.hotelID);
		
	}
	@Test
	public void testpreOrder() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 1;
		preorder.hotelID = 101001;
		preorder.clientRealName="ccc";
		preorder.hotelName = "NumOne";
		preorder.startTime = "2017-01-04 12:00:00";
		preorder.leaveTime = "2017-01-05 12:00:00";
		preorder.roomType = 1;
		preorder.roomTypeName = "";
		preorder.userName = "Teki";
		preorder.price = 300;
		preorder.numOfRoom = 1;
		ResultMessage result1=createorder.preOrder(preorder);
		assertEquals(result1.result,true);
	}
	
	@Test
	public void testconfirm() throws RemoteException{
		PreOrderVO preorder=new PreOrderVO();
		preorder.userID = 1;
		preorder.hotelID = 101001;
		preorder.clientRealName="ccc";
		preorder.hotelName = "NumOne";
		preorder.startTime = "2017-01-05 12:00:00";
		preorder.leaveTime = "2017-01-06 12:00:00";
		preorder.roomType = 1;
		preorder.roomTypeName = "";
		preorder.userName = "Teki";
		preorder.price = 300;
		preorder.numOfRoom = 1;
		ResultMessage result1=createorder.preOrder(preorder);
		assertEquals(result1.result,true);
		OrderStrategyVO orderstrategy=createorder.getStrategy(preorder.userID);
		OrderInfoVO orderInfo=new OrderInfoVO();
		orderInfo.strategyID=orderstrategy.strategyID;
		orderInfo.orderID=orderstrategy.orderID;
		orderInfo.price=orderstrategy.orderPrice;
		orderInfo.userID=preorder.userID;
		orderInfo.hotelID=preorder.hotelID;
		orderInfo.numOfGuest=1;
		orderInfo.kids=0;
		orderInfo.guestRealName="lq";
		orderInfo.latestTime="2017-01-05 16:00:00";
		ResultMessage result2=createorder.confirm(orderInfo);
		assertEquals(result2.result,true);
		
	}
	
}