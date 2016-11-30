package tiquartet.ServerModule.bl.createorderbl;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.dataservice.impl.StrategyDataImpl;
import tiquartet.ServerModule.po.StrategyPO;
import tiquartet.CommonModule.vo.OrderInfoVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrder{	
	
	/**
	 * @param hotelID
	 * @param roomTypeID
	 * @return
	 * @throws RemoteException
	 */
	static List<PreOrderVO> list=new ArrayList<PreOrderVO>();
	DataFactory dataFactory=new DataFactory();
	public List<StrategyVO> getStrategyByID(int userID) throws RemoteException{
		List<StrategyVO> volist=new ArrayList<StrategyVO>();
		List<StrategyPO> polist;
		PreOrderVO vo=new PreOrderVO();
		for(int i=0;i<list.size();i++){
			if(list.get(i).userID==userID){
				vo=list.get(i);
				break;
			}
		}
		polist=StrategyDataImpl.getInstance().searchByHotel(vo.hotelID);
		return volist;
	}
	
	public ResultMessage preOrder(PreOrderVO preOrder)throws RemoteException{
		int credit=Integer.parseInt(dataFactory.getUserDataHelper().getCreditBalance(preOrder.userID).message);
		if(credit<0){
			return new ResultMessage(false,"用户信用值低于0","");
		}
		list.add(preOrder);
		return new ResultMessage(true);
	}
	
	public ResultMessage cancelPreOrder(int userID)throws RemoteException{
		return new ResultMessage(true);
	}
	
	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException{
		return new ResultMessage(true);
	}
	
}
