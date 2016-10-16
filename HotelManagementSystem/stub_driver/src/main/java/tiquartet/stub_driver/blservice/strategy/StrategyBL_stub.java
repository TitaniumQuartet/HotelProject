package tiquartet.stub_driver.blservice.strategy;

import java.util.*;
import tiquartet.client.blservice.strategyblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;


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
