package tiquartet.ClientModule.blservice.createorderblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ClientModule.vo.OrderInfoVO;
import tiquartet.ClientModule.vo.OrderStrategyVO;

public interface CreateOrderBLService {
	public ResultMessage orderInfo(OrderInfoVO orderInfo);
	public void endCreateOrder();
	public OrderStrategyVO getOptimal();
	public List<OrderStrategyVO> otherStrategy();
	public ResultMessage selectStrategy(long strategyID);
}
