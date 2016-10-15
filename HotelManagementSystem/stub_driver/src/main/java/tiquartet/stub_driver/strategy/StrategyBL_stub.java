package tiquartet.stub_driver.strategy;



public class StrategyBL_stub implements StrategyBLService{
	public ResultMessage addStrategy(StrategyVO){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage deleteStrategy(long strategyID){
		return ResultMessage.SUCCEED;
	}
	public List<StrategyVO> getStrategy(long userID){
		return new List<StrategyVO>;
	}
	public ResultMessage changeStrategy(StrategyVO){
		return ResultMessage.SUCCEED;
	}
}
