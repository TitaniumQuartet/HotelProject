package tiquartet.ServerModule.bl.createorderbl;

import java.rmi.RemoteException;
import tiquartet.ServerModule.dataservice.strategydataservice.*;
import tiquartet.ServerModule.po.*;
import tiquartet.ServerModule.data.strategydata.*;

public class StrategyData_driver {
	public void drive(StrategyDataService stub) throws RemoteException{
		stub.insert(new StrategyPO());
		stub.update(new StrategyPO());
		stub.delete(00010);
		stub.searchByHotel(0000011111);
	}
	
	public static void main(String[] args){
	try {
		new StrategyData_driver().drive(new StrategyData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
