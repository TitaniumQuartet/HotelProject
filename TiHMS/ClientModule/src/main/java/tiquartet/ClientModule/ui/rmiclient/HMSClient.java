package tiquartet.ClientModule.ui.rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
	 * 初始化客户端.
	 * @return
	 */
	public ResultMessage init() {
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
			hotelInfoBLSkel = (HotelInfoBLService) registry.lookup("hotelInfo");
			manageOrderBLSkel = (ManageOrderBLService) registry.lookup("manageOrder");
		} catch (RemoteException e) {
			e.printStackTrace();
			return new ResultMessage(false);
		} catch (NotBoundException e) {
			e.printStackTrace();
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}
	
	public CreateOrderBLService getCreateOrderBL() {
		return HMSClient.createOrderBLSkel;
	}
	
	public HotelInfoBLService getHotelInfoBL() {
		return HMSClient.hotelInfoBLSkel;
	}
	
	public ManageOrderBLService getManageOrderBL() {
		return HMSClient.manageOrderBLSkel;
	}
	
	public ManageRoomBLService getManageRoomBL() {
		return HMSClient.manageRoomBLSkel;
	}
	
	public ManageUserBLService getManageUserBL() {
		return HMSClient.manageUserBLSkel;
	}
	
	public SearchHotelBLService getSearchHotelBL() {
		return HMSClient.searchHotelBLSkel;
	}
	
	public StrategyBLService getStrategyBL() {
		return HMSClient.strategyBLSkel;
	}
	
	public UsermainBLService getUserMainBL() {
		return HMSClient.usermainBLSkel;
	}
	
}
