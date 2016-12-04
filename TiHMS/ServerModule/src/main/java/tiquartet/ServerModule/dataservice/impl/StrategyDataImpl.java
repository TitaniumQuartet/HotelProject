package tiquartet.ServerModule.dataservice.impl;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.StrategyDataHelper;
import tiquartet.ServerModule.dataservice.strategydataservice.StrategyDataService;
import tiquartet.ServerModule.po.StrategyPO;

public class StrategyDataImpl implements StrategyDataService{
	
	private StrategyDataHelper strategyDataHelper;
	
	private DataFactoryService dataFactory;

	private static StrategyDataImpl strategyDataServiceImpl;
	
	public static StrategyDataImpl getInstance(){
		if(strategyDataServiceImpl == null){
			strategyDataServiceImpl = new StrategyDataImpl();
		}
		return strategyDataServiceImpl;
	}
	
	public StrategyDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			strategyDataHelper = dataFactory.getStrategyDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	@Override
	public List<StrategyPO> searchByHotel(int hotelID) {
		return strategyDataHelper.searchByHotel(hotelID);
	}

	@Override
	public ResultMessage insert(StrategyPO strategy) {
		return strategyDataHelper.insert(strategy);
	}

	@Override
	public ResultMessage delete(int strategyID) {
		return strategyDataHelper.delete(strategyID);
	}

	@Override
	public ResultMessage update(StrategyPO strategy) {
		return strategyDataHelper.update(strategy);
	}

}
