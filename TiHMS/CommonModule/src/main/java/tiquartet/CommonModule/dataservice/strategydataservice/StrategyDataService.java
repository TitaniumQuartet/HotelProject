package tiquartet.CommonModule.dataservice.strategydataservice;

import java.util.List;

import tiquartet.CommonModule.po.StrategyPO;
import tiquartet.CommonModule.util.ResultMessage;

public interface StrategyDataService {
	public List<StrategyPO> searchByHotel(long hotelID);
	public ResultMessage insert(StrategyPO strategy);
	public ResultMessage delete(long strategyID);
	public ResultMessage update(StrategyPO strategy);

}
