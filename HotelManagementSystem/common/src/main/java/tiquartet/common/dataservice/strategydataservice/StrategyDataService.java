package tiquartet.common.dataservice.strategydataservice;

public interface StrategyDataService {
	public List<StrategyPO> searchByHotel(long hotelID);
	public ResultMessage insert(StrategyPO strategy);
	public ResultMessage delete(long strategyID);
	public ResultMessage update(StrategyPO strategy);

}
