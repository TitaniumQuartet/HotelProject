package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.StrategyPO;

public interface StrategyDataHelper {

	public List <StrategyPO > searchByHotel (int hotelID);
	public ResultMessage insert (StrategyPO strategy);
	public ResultMessage delete (int strategyID);
	public ResultMessage update (StrategyPO strategy);

}
