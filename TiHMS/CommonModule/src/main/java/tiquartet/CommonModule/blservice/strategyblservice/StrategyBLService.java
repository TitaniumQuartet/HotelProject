package tiquartet.CommonModule.blservice.strategyblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;


import tiquartet.CommonModule.vo.StrategyVO;

public interface StrategyBLService {
	public ResultMessage addStrategy(StrategyVO s);
	public ResultMessage deleteStrategy(long strategyID);
	public List<StrategyVO> getStrategy(long userID);
	public ResultMessage changeStrategy(StrategyVO s);
	
}
