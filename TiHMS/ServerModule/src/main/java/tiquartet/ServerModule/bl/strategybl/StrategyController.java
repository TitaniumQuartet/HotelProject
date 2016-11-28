package tiquartet.ServerModule.bl.strategybl;

import java.rmi.RemoteException;
import java.util.List;

import tiquartet.CommonModule.blservice.strategyblservice.StrategyBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.StrategyVO;

public class StrategyController implements StrategyBLService{
    Strategy strategy=new Strategy();
	@Override
	public ResultMessage addStrategy(StrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategy.addStrategy(strategyvo);
	}

	@Override
	public ResultMessage deleteStrategy(int strategyID) throws RemoteException {
		// TODO Auto-generated method stub
		return strategy.deleteStrategy(strategyID);
	}

	@Override
	public List<StrategyVO> searchByHotel(int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return strategy.searchByHotel(hotelID);
	}

	@Override
	public ResultMessage modifyStrategy(StrategyVO newStrategy) throws RemoteException {
		// TODO Auto-generated method stub
		return strategy.modifyStrategy(newStrategy);
	}

}
