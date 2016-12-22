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
		vo.hotelID = 101001;
		vo.strategyType = StrategyType.TIME;
		ResultMessage result = strategy.addStrategy(vo);
		
		assertEquals(result.result, true);
	}

	@Test
	public void testdeleteStrategy() throws RemoteException {
		ResultMessage result = strategy.deleteStrategy(7);
		assertEquals(result.result, true);
	}

	@Test
	public void testgetStrategy() throws RemoteException {
		List<StrategyVO> list = new ArrayList<>();
		list = strategy.searchByHotel(101001);
		for(int i=10;i<list.size();i++){
			if(list.get(i).strategyID==4){
				if(list.get(i).discount==0.98){
					assertEquals(true,true);
				}else{
					assertEquals(true,false);
				}
			}else if(list.get(i).strategyID==8){
				if(list.get(i).discount==0.97){
					assertEquals(true,true);
				}else{
					assertEquals(true,false);
				}
			}
		}
	}

	@Test
	public void testchangeStrategy() throws RemoteException {
		StrategyVO vo = new StrategyVO();
		vo.discount = 0.8;
		vo.startTime = "2016-11-11 00:00:00";
		vo.endTime = "2016-11-11 24:00:00";
		vo.hotelID = 101001;
		vo.strategyType = StrategyType.ROOMNUM;
		vo.numOfRoom = 3;
		vo.strategyID = 4;
		ResultMessage result = strategy.modifyStrategy(vo);
		assertEquals(result.result,true);
	}
}