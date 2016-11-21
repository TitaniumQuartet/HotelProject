package tiquartet.ServerModule.bl.strategybl;

import java.util.*;
import tiquartet.CommonModule.blservice.strategyblservice.*;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;


public class StrategyBL_stub implements StrategyBLService{
	public ResultMessage addStrategy(StrategyVO s){
		return new ResultMessage(true);
	}
	public ResultMessage deleteStrategy(long strategyID){
		return new ResultMessage(true);
	}
	public List<StrategyVO> getStrategy(long userID){
		return new ArrayList<StrategyVO>();
	}
	public ResultMessage changeStrategy(StrategyVO s){
		return new ResultMessage(true);
	}
}
