package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;


import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.bl.createorderbl.CreateOrder;
import tiquartet.ServerModule.bl.createorderbl.StrategyListItem;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder order;
	
	@Test
	public void testgetStrategyByID() throws RemoteException{
		order = new CreateOrder();
		StrategyListItem strategy = order.getStrategyByID(123456, 666666);
		assertEquals(strategy.intro, "20% dicount");
	}
	
	@Test
	public void testpreOrder() throws RemoteException{
		order = new CreateOrder();
		PreOrderVO pre = new PreOrderVO();
		ResultMessage result = order.preOrder(pre);
		assertEquals(result.result, true);
	}
	
	@Test
	public void testcancelPreOrder() throws RemoteException{
		order = new CreateOrder();
		ResultMessage result = order.cancelPreOrder();
		assertEquals(result.result, true);
	}
	
	@Test
	public void testconfirm() throws RemoteException{
		order = new CreateOrder();
		OrderInfoVO vo = new OrderInfoVO();
		ResultMessage result = order.confirm(vo);
		assertEquals(result.result, true);
	}
}