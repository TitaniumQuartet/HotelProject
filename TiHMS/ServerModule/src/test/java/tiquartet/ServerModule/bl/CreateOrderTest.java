package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;


import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.bl.createorderbl.CreateOrder;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder createorder;
	@Test
	public void testgetStrategyByID() throws RemoteException{
	}
	
	@Test
	public void testpreOrder() throws RemoteException{
		createorder = new CreateOrder();
		PreOrderVO preorder=new PreOrderVO();
		ResultMessage result = createorder.preOrder(preorder);
		assertEquals(result.result, false);
		preorder.userID = 123456;
		preorder.hotelID = 123456;
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
		preorder.hotelID = 123456;
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
		createorder = new CreateOrder();
		OrderInfoVO vo = new OrderInfoVO();
		//ResultMessage result = order.confirm(vo);
		assertEquals(true, true);
	}
}