package tiquartet.stub_driver.stategydata;

import tiquartet.common.dataservice.strategydataservice;

public class StrategyData_stub {
	public List<StrategyPO> searchByHotel(long hotelID){
		return new List<Strategy>();
	}
	public ResultMessage insert(StrategyPO strategy){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage delete(long strategyID){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(StrategyPO strategy){
		return ResultMessage.SUCCEED;
	}

}
