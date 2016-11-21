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
		return new ResultMessage(true);
	}
	public ResultMessage delete(long strategyID){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public ResultMessage update(StrategyPO strategy){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	public List<StrategyPO> searchByHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultMessage delete(int strategyID) {
		// TODO Auto-generated method stub
		return null;
	}

}
