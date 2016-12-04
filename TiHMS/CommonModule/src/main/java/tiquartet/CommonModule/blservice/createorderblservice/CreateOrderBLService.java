package tiquartet.CommonModule.blservice.createorderblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;
import tiquartet.CommonModule.vo.PreOrderVO;

public interface CreateOrderBLService extends Remote{
	/**
	 *预定了某日期的房间，系统改变该房间的状态
	 * @param preOrder
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage preOrder(PreOrderVO preOrder) throws RemoteException;
	/**
	 * 取消预定的订单
	 * @param preOrderID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage cancelPreOrder(int userID) throws RemoteException;
	/**
	 * 获得与订单相关的策略
	 * @param preOrderID
	 * @return
	 * @throws RemoteException
	 */
	public OrderStrategyVO getStrategy(int userID) throws RemoteException;
	/**
	 * 订单完成，系统保存完整订单信息
	 * @param orderInfo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException;
	
	/**线下入住
	 * @param preOrder
	 * @return
	 * @throws RemoteException
	 */
	public List<String> offLine(PreOrderVO preOrder) throws RemoteException;
}
