package tiquartet.ServerModule.bl.createorderbl;

import java.rmi.RemoteException;
import tiquartet.ServerModule.dataservice.userdataservice.*;
import tiquartet.ServerModule.po.*;
import tiquartet.ServerModule.data.userdata.*;

public class UserData_driver {
	public void drive(UserDataService stub) throws RemoteException{
		stub.insert(new UserPO());
		stub.update(new UserPO());
		stub.insert(new UserPO());
		stub.getUser(0000011111);
		stub.getCreditBalance(0000011111);
		stub.searchUser("s","s");
	}
	
	public static void main(String[] args){
	try {
		new UserData_driver().drive(new UserData_stub());
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	}

}
