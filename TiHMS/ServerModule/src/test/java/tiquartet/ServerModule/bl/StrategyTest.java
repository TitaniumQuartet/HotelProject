package tiquartet.ServerModule.bl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.ServerModule.bl.strategybl.Strategy;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.StrategyType;

public class StrategyTest {

	private Strategy strategy = new Strategy();

	@Test
	public void testaddStartegy() throws RemoteException {
		StrategyVO vo = new StrategyVO();
		vo.discount = 0.8;
		vo.startTime = "2016-11-11 00:00:00";
		vo.endTime = "2016-11-11 24:00:00";
		vo.hotelID = 01023004;
		vo.strategyType = StrategyType.TIME;
		ResultMessage result = strategy.addStrategy(vo);
		assertEquals(result.result, true);
	}

	@Test
	public void testdeleteStrategy() throws RemoteException {
		StrategyVO vo = new StrategyVO();
		vo.discount = 0.8;
		vo.startTime = "2016-11-11 00:00:00";
		vo.endTime = "2016-11-11 24:00:00";
		vo.hotelID = 01023004;
		vo.strategyType = StrategyType.TIME;
		vo.strategyID = 00001111;
	    strategy.addStrategy(vo);
		ResultMessage result = strategy.deleteStrategy(00001111);
		assertEquals(result.result, true);
	}

	@Test
	public void testgetStrategy() throws RemoteException {
		StrategyVO vo = new StrategyVO();
		vo.discount = 0.8;
		vo.startTime = "2016-11-11 00:00:00";
		vo.endTime = "2016-11-11 24:00:00";
		vo.hotelID = 01023004;
		vo.strategyType = StrategyType.TIME;
		strategy.addStrategy(vo);
		StrategyVO vo2 = new StrategyVO();
		vo2.discount = 0.6;
		vo2.startTime = "2016-12-12 00:00:00";
		vo2.endTime = "2016-12-12 24:00:00";
		vo2.hotelID = 01023004;
		vo2.strategyType = StrategyType.TIME;
		List<StrategyVO> list = new ArrayList<>();
		list = strategy.searchByHotel(01023004);
		assertEquals(vo, list.get(0));
		assertEquals(vo2, list.get(1));
	}

	@Test
	public void testchangeStrategy() throws RemoteException {
		StrategyVO vo = new StrategyVO();
		vo.discount = 0.8;
		vo.startTime = "2016-11-11 00:00:00";
		vo.endTime = "2016-11-11 24:00:00";
		vo.hotelID = 01023004;
		vo.strategyType = StrategyType.TIME;
		vo.strategyID = 00001111;
	    strategy.addStrategy(vo);
	    vo.discount = 0.8;
		ResultMessage result = strategy.modifyStrategy(vo);
		assertEquals(result.result,true);
	}
}