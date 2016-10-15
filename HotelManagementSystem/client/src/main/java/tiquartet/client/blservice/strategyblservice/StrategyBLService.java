package tiquartet.client.blservice.strategyblservice;

public interface StrategyBLService {
	public ResultMessage addStrategy(StrategyVO);
	public ResultMessage deleteStrategy(long strategyID);
	public List<StrategyVO> getStrategy(long userID);
	public ResultMessage changeStrategy(StrategyVO);
	
}
