package tiquartet.stub_driver.dataservice.strategydata;

import tiquartet.common.dataservice.strategydataservice;
import java.util.List;
public class StrategyData_stub implements StrategyDtaService{
	public List<StrategyPO> searchByHotel(long hotelID){
		System.out.println("yes");
		return new List<Strategy>();
	}
	public ResultMessage insert(StrategyPO strategy){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage delete(long strategyID){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(StrategyPO strategy){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}

}