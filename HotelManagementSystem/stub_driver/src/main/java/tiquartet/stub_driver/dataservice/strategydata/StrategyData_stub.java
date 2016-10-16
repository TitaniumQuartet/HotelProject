package tiquartet.stub_driver.dataservice.strategydata;

import java.util.*;
import tiquartet.common.dataservice.strategydataservice.*;
import tiquartet.common.po.*;
import tiquartet.common.util.ResultMessage;


public class StrategyData_stub implements StrategyDataService{
	public List<StrategyPO> searchByHotel(long hotelID){
		System.out.println("yes");
		return new ArrayList<StrategyPO>();
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
