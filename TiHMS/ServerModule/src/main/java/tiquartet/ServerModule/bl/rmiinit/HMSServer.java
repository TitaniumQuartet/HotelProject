package tiquartet.ServerModule.bl.rmiinit;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.bl.hotelinfobl.HotelInfoController;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;

/**
 * 酒店管理系统的服务器类，可建立RMI服务，运行服务器.
 * @author greatlyr
 */
public class HMSServer {
	/**
	 * 服务器初始化，提供RMI服务.
	 * @return 启动服务器的结果信息
	 */
	public ResultMessage init() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry();
			HotelInfoController hotelInfoStub = (HotelInfoController) UnicastRemoteObject.exportObject(new HotelInfoController(), 0);
			registry.bind("hotelInfo", hotelInfoStub);
			ManageOrderController manageOrderStub = (ManageOrderController) UnicastRemoteObject.exportObject(new ManageOrderController(), 0);
			registry.bind("manageOrder", manageOrderStub);
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}
}
