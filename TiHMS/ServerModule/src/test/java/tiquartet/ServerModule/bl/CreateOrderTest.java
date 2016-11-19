package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;
import org.junit.Test;


import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.bl.createorderbl.CreateOrder;
import tiquartet.ServerModule.bl.createorderbl.StrategyListItem;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrderTest{
	
	private CreateOrder order;
	
	@Test
	public void testgetStrategyByID(){
		order = new CreateOrder();
		StrategyListItem strategy = order.getStrategyByID(123456, 666666);
		assertEquals(strategy.intro, "20% dicount");
	}
	
	@Test
	public void testpreOrder(){
		order = new CreateOrder();
		PreOrderVO pre = new PreOrderVO();
		ResultMessage result = order.preOrder(pre);
		assertEquals(result, ResultMessage.SUCCEED);
	}
	
	@Test
	public void testcancelPreOrder(){
		order = new CreateOrder();
		ResultMessage result = order.cancelPreOrder();
		assertEquals(result, ResultMessage.SUCCEED);
	}
	
	@Test
	public void testconfirm(){
		order = new CreateOrder();
		OrderInfoVO vo = new OrderInfoVO();
		ResultMessage result = order.confirm(vo);
		assertEquals(result, ResultMessage.SUCCEED);
	}
}