package tiquartet.ServerModule.bl.createorderbl;

import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.StrategyVO;

public class CreateOrderController {
         CreateOrder createorder=new CreateOrder();
         public List<StrategyVO> getStrategyByID(int userID) throws RemoteException{
     		
     		return createorder.getStrategyByID(userID);
     	}
     	
     	public ResultMessage preOrder(PreOrderVO preOrder)throws RemoteException{
     		
     		return createorder.preOrder(preOrder);
     	}
     	
     	public ResultMessage cancelPreOrder(int userID)throws RemoteException{
     		return createorder.cancelPreOrder(userID);
     	}
     	
     	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException{
     		return createorder.confirm(orderInfo);
     	}
}
