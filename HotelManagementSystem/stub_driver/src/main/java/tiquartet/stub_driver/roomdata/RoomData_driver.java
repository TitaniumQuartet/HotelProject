package tiquartet.stub_driver.roomdata;

import java.rmi.RemoteException;
import tiquartet.common.dataservice.roomdataservice;


public class RoomData_driver {
	public void drive(RoomDataService stub) throws RemoteException{
		stub.insert(new RoomPO());
		stub.update(new RoomPO());
		stub.delete(00010);
	}
	
	public static void main(String[] args){
	try {
		new RoomData_driver().drive(new RoomData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
