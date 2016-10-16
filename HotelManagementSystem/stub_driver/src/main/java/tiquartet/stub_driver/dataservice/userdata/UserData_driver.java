package tiquartet.stub_driver.dataservice.userdata;

import java.rmi.RemoteException;
import tiquartet.common.dataservice.userdataservice;

public class UserData_driver {
	public void drive(UserDataService stub) throws RemoteException{
		stub.insert(new UserPO());
		stub.update(new PersonalPO());
		stub.insert(new PersonalPO());
		stub.getUserInfo(0000011111);
		stub.getCreditBalance(0000011111);
		stub.search("s","s");
	}
	
	public static void main(String[] args){
	try {
		new UserData_driver().drive(new UserData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
