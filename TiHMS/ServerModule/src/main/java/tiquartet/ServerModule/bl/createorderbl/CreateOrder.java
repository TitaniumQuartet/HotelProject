package tiquartet.ServerModule.bl.createorderbl;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.OrderInfoVO;

import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrder{
	
	StrategyList list = new StrategyList();
	
	public StrategyListItem getStrategyByID(long hotelID, long roomTypeID) throws RemoteException{
		return list.getStrategy(hotelID, roomTypeID);
	}
	
	public ResultMessage preOrder(PreOrderVO preOrder)throws RemoteException{
		return new ResultMessage(true);
	}
	
	public ResultMessage cancelPreOrder()throws RemoteException{
		return new ResultMessage(true);
	}
	
	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException{
		return new ResultMessage(true);
	}
	
}
