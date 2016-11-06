package tiquartet.ServerModule.bl.strategybl;

import java.util.List;
import java.util.ArrayList;
import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.CommonModule.util.ResultMessage;

public class Strategy{
	
	
	public ResultMessage addStrategy(int hotelID, StrategyVO vo){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage deleteStrategy(long strategyID){
		return ResultMessage.SUCCEED;
	}
	
	public List<StrategyVO> getStrategy(long userID){
		return new ArrayList<StrategyVO>();
	}
	
	public ResultMessage changeStrategy(StrategyVO vo){
		return ResultMessage.SUCCEED;
	}
	
}