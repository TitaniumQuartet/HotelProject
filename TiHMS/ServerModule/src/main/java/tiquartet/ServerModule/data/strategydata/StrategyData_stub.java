package tiquartet.ServerModule.data.strategydata;

import java.util.*;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.strategydataservice.*;
import tiquartet.ServerModule.po.*;


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
