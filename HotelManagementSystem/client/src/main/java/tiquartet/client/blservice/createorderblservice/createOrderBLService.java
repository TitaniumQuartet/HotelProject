package tiquartet.client.blservice.createorderblservice;

public interface createOrderBLService {
	public ResultMessage orderInfo(OrderInfoVO orderInfo);
	public void endCreateOrder();
	public OrderStrategyVO getOptimal();
	public List<OrderStrategyVO> otherStrategy();
	public ResultMessage selectStrategy(long strategyID);
}
