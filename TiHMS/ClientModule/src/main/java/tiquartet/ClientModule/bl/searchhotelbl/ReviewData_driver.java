package tiquartet.ClientModule.bl.searchhotelbl;

import java.rmi.RemoteException;
import tiquartet.CommonModule.dataservice.reviewdataservice.*;
import tiquartet.CommonModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.data.reviewdata.*;

public class ReviewData_driver {
	public void drive(ReviewDataService stub) throws RemoteException{
		stub.insert(new ReviewPO());
		stub.search(0000011111);
	}
	
	public static void main(String[] args){
	try {
		new ReviewData_driver().drive(new ReviewData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
