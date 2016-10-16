package tiquartet.stub_driver.dataservice.orderdata;

import java.rmi.RemoteException;
import tiquartet.common.dataservice.orderdataservice;

public class OrderData_driver {
	public void drive(OrderDataService stub) throws RemoteException{
		stub.getHotelList(0000011111);
		stub.insert(new OrderPO());
		stub.update(new OrderPO());
		stub.hasbeenordered(0000011111,0000011111);
		stub.getOrderPage(0000011111,new OrderFilterPO,1);
	}
	
	public static void main(String[] args){
	try {
		new OrderData_driver().drive(new OrderData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}