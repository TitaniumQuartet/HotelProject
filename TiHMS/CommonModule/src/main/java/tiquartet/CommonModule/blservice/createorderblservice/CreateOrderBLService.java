package tiquartet.CommonModule.blservice.createorderblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;
import tiquartet.CommonModule.vo.PreOrderVO;

public interface CreateOrderBLService extends Remote{
	public ResultMessage prePrder(PreOrderVO preOrder) throws RemoteException;
	public ResultMessage cancelPreOrder(long preOrderID) throws RemoteException;
	public List<OrderStrategyVO> getStrategy(long preOrderID) throws RemoteException;
	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException;
}
