package tiquartet.ServerModule.bl.createorderbl;

import java.rmi.RemoteException;
import java.util.*;
import tiquartet.ServerModule.dataservice.roomdataservice.*;
import tiquartet.ServerModule.po.*;
import tiquartet.ServerModule.data.roomdata.*;

public class RoomData_driver {
	public void drive(RoomDataService stub) throws RemoteException{
		stub.insert(new RoomPO());
		stub.update(new RoomPO());
		stub.delete(00010);
		stub.availableRoomType(0000011111, "2016/5/13", "2016/5/15", 3);		
		stub.checkIn(00010);
		stub.checkOut(00010);
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
