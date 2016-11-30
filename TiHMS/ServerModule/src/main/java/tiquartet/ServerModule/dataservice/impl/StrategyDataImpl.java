package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.StrategyDataHelper;
import tiquartet.ServerModule.dataservice.strategydataservice.StrategyDataService;
import tiquartet.ServerModule.po.StrategyPO;

public class StrategyDataImpl implements StrategyDataService{

	private Map<Integer, StrategyPO> map;
	
	private StrategyDataHelper strategyDataHelper;
	
	private DataFactoryService dataFactory;

	private static StrategyDataImpl strategyDataServiceImpl;
	
	public static StrategyDataImpl getInstance(int hotelID){
		if(strategyDataServiceImpl == null){
			strategyDataServiceImpl = new StrategyDataImpl(hotelID);
		}
		return strategyDataServiceImpl;
	}
	
	public StrategyDataImpl(int hotelID){
		if(map == null){
			dataFactory = new DataFactory();
			strategyDataHelper = dataFactory.getStrategyDataHelper();
			map = strategyDataHelper.searchByHotel(hotelID);
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	@Override
	public List<StrategyPO> searchByHotel(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(StrategyPO strategy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(int strategyID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(StrategyPO strategy) {
		// TODO Auto-generated method stub
		return null;
	}

}
