package tiquartet.stub_driver.dataservice.hotelinfodata;

import java.rmi.RemoteException;

import tiquartet.common.hotelinfodata.HotelInfoDataService;

public class HotelInfoData_driver {
	public void drive(HotelInfoDataService stub) throws RemoteException{
		stub.getHotelInfo(0000011111);
		stub.updateRate(0000011111, 3);
		stub.insert(new HotelDetailsPO());
		stub.update(new HotelInfoPO());
		stub.getRoomType(111,222);
		stub.getRoomTypes(111);
		stub.getHotelList(00011);
	}
	
	public static void main(String[] args){
	try {
		new HotelInfoData_driver().drive(new HotelInfoData_stub());
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}

}
