package tiquartet.stub_driver.blservice.createorder;



public class CreateOrderBL_stub implements CreateOrderBLService{
	public ResultMessage orderInfo(OrderInfoVO orderInfo){
		return ResultMessage.SUCCEED;
	}
	public void endCreateOrder(){
		
	}
	public OrderStrategyVO getOptimal(){
		return new OrderStrategyVO;
	}
	public List<OrderStrategyVO> otherStrategy(){
		return new List<OrderStrategyVO>;
	}
	public ResultMessage selectStrategy(long strategyID){
		return ResultMessage.SUCCEED;
	}
}
