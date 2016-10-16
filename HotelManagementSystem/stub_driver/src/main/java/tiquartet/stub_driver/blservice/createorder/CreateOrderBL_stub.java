package tiquartet.stub_driver.blservice.createorder;

import java.util.List;
import java.util.ArrayList;
import tiquartet.client.blservice.createorderblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;

public class CreateOrderBL_stub implements CreateOrderBLService{
	public ResultMessage orderInfo(OrderInfoVO orderInfo){
		return ResultMessage.SUCCEED;
	}
	public void endCreateOrder(){
		
	}
	public OrderStrategyVO getOptimal(){
		return new OrderStrategyVO();
	}
	public List<OrderStrategyVO> otherStrategy(){
		return new ArrayList<OrderStrategyVO>();
	}
	public ResultMessage selectStrategy(long strategyID){
		return ResultMessage.SUCCEED;
	}
}
