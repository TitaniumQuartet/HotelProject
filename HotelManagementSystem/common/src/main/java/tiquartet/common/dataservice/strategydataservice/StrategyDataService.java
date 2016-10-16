package tiquartet.common.dataservice.strategydataservice;

import java.util.List;
import tiquartet.common.po.strategypo.StrategyPO;
import tiquartet.common.util.resultmessage.ResultMessage;

public interface StrategyDataService {
	public List<StrategyPO> searchByHotel(long hotelID);
	public ResultMessage insert(StrategyPO strategy);
	public ResultMessage delete(long strategyID);
	public ResultMessage update(StrategyPO strategy);

}
