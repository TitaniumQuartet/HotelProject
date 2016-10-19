package tiquartet.StubDriver.blservice.strategy;

import java.util.*;
import tiquartet.ClientModule.blservice.strategyblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;


public class StrategyBL_stub implements StrategyBLService{
	public ResultMessage addStrategy(StrategyVO s){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage deleteStrategy(long strategyID){
		return ResultMessage.SUCCEED;
	}
	public List<StrategyVO> getStrategy(long userID){
		return new ArrayList<StrategyVO>();
	}
	public ResultMessage changeStrategy(StrategyVO s){
		return ResultMessage.SUCCEED;
	}
}
