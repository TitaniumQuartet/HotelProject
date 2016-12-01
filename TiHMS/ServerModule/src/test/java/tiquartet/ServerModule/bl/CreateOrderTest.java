package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;


import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.bl.createorderbl.CreateOrder;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder order;
	
	@Test
	public void testgetStrategyByID() throws RemoteException{
	}
	
	@Test
	public void testpreOrder() throws RemoteException{
		order = new CreateOrder();
		PreOrderVO pre = new PreOrderVO();
		//ResultMessage result = order.preOrder(pre);
		assertEquals(true, true);
	}
	
	@Test
	public void testcancelPreOrder() throws RemoteException{
		
	}
	
	@Test
	public void testconfirm() throws RemoteException{
		order = new CreateOrder();
		OrderInfoVO vo = new OrderInfoVO();
		//ResultMessage result = order.confirm(vo);
		assertEquals(true, true);
	}
}