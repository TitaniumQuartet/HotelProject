package tiquartet.ServerModule.dataservice.strategydataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.StrategyPO;

public interface StrategyDataService {
	public List<StrategyPO> searchByHotel(long hotelID);
	public ResultMessage insert(StrategyPO strategy);
	public ResultMessage delete(long strategyID);
	public ResultMessage update(StrategyPO strategy);

}
