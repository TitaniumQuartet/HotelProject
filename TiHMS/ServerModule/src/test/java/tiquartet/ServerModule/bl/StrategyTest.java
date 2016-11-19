package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.ServerModule.bl.strategybl.Strategy;
import tiquartet.CommonModule.util.ResultMessage;

public class StrategyTest{
	
	private Strategy strategy;
	
	@Test
	public void testaddStartegy(){
		Strategy startegy = new Strategy();
		StrategyVO vo = new StrategyVO();
		ResultMessage result = startegy.addStrategy(123456789, vo);
		assertEquals(result, ResultMessage.SUCCEED);
	}
	
	@Test
	public void testdeleteStrategy(){
		strategy = new Strategy();
		ResultMessage result = strategy.deleteStrategy(00001111);
		assertEquals(result, ResultMessage.SUCCEED);
	}
	
	@Test
	public void testgetStrategy(){
		strategy = new Strategy();
		List<StrategyVO> list = strategy.getStrategy(00000011);
		assertEquals(list, list);
	}
	
	@Test
	public void testchangeStrategy(){
		strategy = new Strategy();
		StrategyVO vo = new StrategyVO();
		ResultMessage result = strategy.changeStrategy(vo);
		assertEquals(result, ResultMessage.SUCCEED);
	}
}