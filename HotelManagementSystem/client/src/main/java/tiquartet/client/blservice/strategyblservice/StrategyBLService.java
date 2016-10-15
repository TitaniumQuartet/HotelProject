package tiquartet.client.blservice.strategyblservice;

import java.util.List;

import tiquartet.client.vo.StrategyVO;

public interface StrategyBLService {
	public ResultMessage addStrategy(StrategyVO s);
	public ResultMessage deleteStrategy(long strategyID);
	public List<StrategyVO> getStrategy(long userID);
	public ResultMessage changeStrategy(StrategyVO s);
	
}
