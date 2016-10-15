package tiquartet.client.blservice.createorderblservice;

import java.util.List;

import tiquartet.client.vo.OrderInfoVO;
import tiquartet.client.vo.OrderStrategyVO;

public interface CreateOrderBLService {
	public ResultMessage orderInfo(OrderInfoVO orderInfo);
	public void endCreateOrder();
	public OrderStrategyVO getOptimal();
	public List<OrderStrategyVO> otherStrategy();
	public ResultMessage selectStrategy(long strategyID);
}
