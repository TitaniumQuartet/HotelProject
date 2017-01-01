package tiquartet.ServerModule.bl.rmiinit;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import tiquartet.CommonModule.blservice.createorderblservice.CreateOrderBLService;
import tiquartet.CommonModule.blservice.hotelinfoblservice.HotelInfoBLService;
import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.blservice.strategyblservice.StrategyBLService;
import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.bl.createorderbl.CreateOrderController;
import tiquartet.ServerModule.bl.hotelinfobl.HotelInfoController;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;
import tiquartet.ServerModule.bl.manageroombl.ManageRoomController;
import tiquartet.ServerModule.bl.manageuserbl.ManageUserController;
import tiquartet.ServerModule.bl.searchhotelbl.SearchHotelController;
import tiquartet.ServerModule.bl.strategybl.StrategyController;
import tiquartet.ServerModule.bl.usermainbl.UserMainController;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.impl.StrategyDataImpl;
import tiquartet.ServerModule.po.StrategyPO;

/**
 * 酒店管理系统的服务器类，可建立RMI服务，运行服务器.
 * 
 * @author greatlyr
 */
public class HMSServer {
	
	/**
	 * 运行服务器程序.
	 * @param args
	 */
	public static void main(String[] args){
		HMSServer server = new HMSServer();
		server.init();
		DataThread dataThread=new DataThread();
		dataThread.start();
	}

	/**
	 * 服务器初始化，提供RMI服务.
	 * 
	 * @return 启动服务器的结果信息
	 */
	public ResultMessage init() {
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			
			CreateOrderBLService createOrderStub = (CreateOrderBLService) UnicastRemoteObject
					.exportObject(new CreateOrderController(), 0);
			registry.rebind("createOrder", createOrderStub);
			
			HotelInfoBLService hotelInfoStub = (HotelInfoBLService) UnicastRemoteObject
					.exportObject(new HotelInfoController(), 0);
			registry.rebind("hotelInfo", hotelInfoStub);
			
			ManageOrderBLService manageOrderStub = (ManageOrderBLService) UnicastRemoteObject
					.exportObject(new ManageOrderController(), 0);
			registry.rebind("manageOrder", manageOrderStub);
			
			ManageRoomBLService manageRoomStub = (ManageRoomBLService) UnicastRemoteObject
					.exportObject(new ManageRoomController(), 0);
			registry.rebind("manageRoom", manageRoomStub);
			
			ManageUserBLService manageUserStub = (ManageUserBLService) UnicastRemoteObject
					.exportObject(new ManageUserController(), 0);
			registry.rebind("manageUser", manageUserStub);
			
			SearchHotelBLService searchHotelStub = (SearchHotelBLService) UnicastRemoteObject
					.exportObject(new SearchHotelController(), 0);
			registry.rebind("searchHotel", searchHotelStub);
			
			StrategyBLService strategyStub = (StrategyBLService) UnicastRemoteObject
					.exportObject(new StrategyController(), 0);
			registry.rebind("strategy", strategyStub);
			
			UsermainBLService userMainStub = (UsermainBLService) UnicastRemoteObject
					.exportObject(new UserMainController(), 0);
			registry.rebind("userMain", userMainStub);
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}

}

class DataThread extends Thread {
    public void run() {
    	OrderDataImpl impl=new OrderDataImpl();
        while(true){
            try {
                sleep(60*1000);
                impl.updateState();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
