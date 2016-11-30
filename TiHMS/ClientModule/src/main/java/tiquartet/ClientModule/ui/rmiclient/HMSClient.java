package tiquartet.ClientModule.ui.rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import tiquartet.CommonModule.blservice.createorderblservice.CreateOrderBLService;
import tiquartet.CommonModule.blservice.hotelinfoblservice.HotelInfoBLService;
import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.blservice.manageroomblservice.ManageRoomBLService;
import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.CommonModule.blservice.searchhotelblservice.SearchHotelBLService;
import tiquartet.CommonModule.blservice.strategyblservice.StrategyBLService;
import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;

/**
 * RMI客户端.
 * 
 * @author greatlyr
 *
 */
public class HMSClient {

	static CreateOrderBLService createOrderBLSkel;

	static HotelInfoBLService hotelInfoBLSkel;

	static ManageOrderBLService manageOrderBLSkel;

	static ManageRoomBLService manageRoomBLSkel;

	static ManageUserBLService manageUserBLSkel;

	static SearchHotelBLService searchHotelBLSkel;

	static StrategyBLService strategyBLSkel;

	static UsermainBLService usermainBLSkel;

	/**
	 * 运行客户端程序.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HMSClient client=new HMSClient();
		client.init();
		Application.launch(ClientApp.class, args);
	}

	/**
	 * 初始化客户端.
	 * 
	 * @return
	 */
	public ResultMessage init() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			hotelInfoBLSkel = (HotelInfoBLService) registry.lookup("hotelInfo");
			manageOrderBLSkel = (ManageOrderBLService) registry
					.lookup("manageOrder");
		} catch (RemoteException e) {
			//远程连接失败
			e.printStackTrace();
			return new ResultMessage(false);
		} catch (NotBoundException e) {
			//没有找到相应的RMI服务
			e.printStackTrace();
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}

	/**
	 * 以下为获取各个RMI接口的静态方法.
	 * 
	 * @return
	 */
	public static CreateOrderBLService getCreateOrderBL() {
		return HMSClient.createOrderBLSkel;
	}

	public static HotelInfoBLService getHotelInfoBL() {
		return HMSClient.hotelInfoBLSkel;
	}

	public static ManageOrderBLService getManageOrderBL() {
		return HMSClient.manageOrderBLSkel;
	}

	public static ManageRoomBLService getManageRoomBL() {
		return HMSClient.manageRoomBLSkel;
	}

	public static ManageUserBLService getManageUserBL() {
		return HMSClient.manageUserBLSkel;
	}

	public static SearchHotelBLService getSearchHotelBL() {
		return HMSClient.searchHotelBLSkel;
	}

	public static StrategyBLService getStrategyBL() {
		return HMSClient.strategyBLSkel;
	}

	public static UsermainBLService getUserMainBL() {
		return HMSClient.usermainBLSkel;
	}

}
