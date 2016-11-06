package tiquartet.ServerModule.bl.createorderbl;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrder{
	
	StrategyList list = new StrategyList();
	
	public StrategyListItem getStrategyByID(long hotelID, long roomTypeID){
		return list.getStrategy(hotelID, roomTypeID);
	}
	
	public ResultMessage preOrder(PreOrderVO preOrder){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage cancelPreOrder(){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage confirm(OrderInfoVO orderInfo){
		return ResultMessage.SUCCEED;
	}
	
}
