package tiquartet.ServerModule.bl.strategybl;

import java.util.List;
import java.rmi.RemoteException;
import java.util.ArrayList;

import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.dataservice.impl.StrategyDataImpl;
import tiquartet.ServerModule.po.StrategyPO;
import tiquartet.CommonModule.util.ResultMessage;

public class Strategy{
	
	DataFactory dataFactory = new DataFactory();
	//增加策略
	public ResultMessage addStrategy(StrategyVO strategyvo)throws RemoteException{
		StrategyPO po=new StrategyPO(strategyvo);
		dataFactory.getStrategyDataHelper().insert(po);
		return new ResultMessage(true);
		//return dataFactory.getStrategyDataHelper().insert(po);
	}
	//删除策略
	public ResultMessage deleteStrategy(int strategyID)throws RemoteException{
		dataFactory.getStrategyDataHelper().delete(strategyID);
		return new ResultMessage(true);
	}
	//根据酒店编号搜索策略
	public List<StrategyVO> searchByHotel(int hotelID)throws RemoteException{
		List<StrategyPO> polist=StrategyDataImpl.getInstance().searchByHotel(hotelID);
		List<StrategyVO> volist=new ArrayList<StrategyVO>();
		for(int i=0;i<polist.size();i++){
			StrategyVO vo=polist.get(i).toStrategyvo();			
			volist.add(vo);
		}
		return volist;
	}
	
	public ResultMessage modifyStrategy(StrategyVO strategyvo)throws RemoteException{
		StrategyPO po=new StrategyPO(strategyvo);
		dataFactory.getStrategyDataHelper().update(po);
		return new ResultMessage(true);
	}
	
}