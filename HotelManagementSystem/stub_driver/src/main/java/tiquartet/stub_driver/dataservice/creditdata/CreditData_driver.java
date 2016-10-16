package tiquartet.stub_driver.dataservice.creditdata;

import java.rmi.RemoteException;
import tiquartet.common.dataservice.creditdataservice;

public class CreditData_driver {
	public void drive(CreditDataService stub) throws RemoteException{
		stub.getRecord(0000011111);
	}
	
	public static void main(String[] args){
	try {
		new CreditData_driver().drive(new CreditData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
