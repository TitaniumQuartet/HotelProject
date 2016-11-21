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
		return new ResultMessage(true);
	}
	
	public ResultMessage cancelPreOrder(){
		return new ResultMessage(true);
	}
	
	public ResultMessage confirm(OrderInfoVO orderInfo){
		return new ResultMessage(true);
	}
	
}
