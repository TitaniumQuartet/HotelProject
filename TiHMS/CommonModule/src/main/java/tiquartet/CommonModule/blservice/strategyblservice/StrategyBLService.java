package tiquartet.CommonModule.blservice.strategyblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;


import tiquartet.CommonModule.vo.StrategyVO;

public interface StrategyBLService extends Remote{
	//添加策略
	public ResultMessage addStrategy(StrategyVO strategy) throws RemoteException;
	//删除策略
	public ResultMessage deleteStrategy(int strategyID) throws RemoteException;
	//获得酒店策略
	public List<StrategyVO> searchByHotel(int hotelID) throws RemoteException;
	//改变策略
	public ResultMessage modifyStrategy(StrategyVO newStrategy) throws RemoteException;
	
}
