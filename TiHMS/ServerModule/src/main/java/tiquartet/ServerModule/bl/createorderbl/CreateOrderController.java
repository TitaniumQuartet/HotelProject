package tiquartet.ServerModule.bl.createorderbl;

import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;
import tiquartet.CommonModule.vo.PreOrderVO;

public class CreateOrderController {
         CreateOrder createorder=new CreateOrder();
         public OrderStrategyVO getStrategy(int userID) throws RemoteException{
     		
     		return createorder.getStrategy(userID);
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
