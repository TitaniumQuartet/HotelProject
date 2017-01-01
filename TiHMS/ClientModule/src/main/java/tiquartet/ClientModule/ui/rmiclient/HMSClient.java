package tiquartet.ClientModule.ui.rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tiquartet.ClientModule.ui.adminui.AdminMainController;
import tiquartet.ClientModule.ui.clientui.ClientMainController;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.hotelierui.HotelierMainController;
import tiquartet.ClientModule.ui.marketerui.MarketerMainController;
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

	// 客户端程序的主窗口
	private static Stage mainStage = null;

	static CreateOrderBLService createOrderBLSkel;

	static HotelInfoBLService hotelInfoBLSkel;

	static ManageOrderBLService manageOrderBLSkel;

	static ManageRoomBLService manageRoomBLSkel;

	static ManageUserBLService manageUserBLSkel;

	static SearchHotelBLService searchHotelBLSkel;

	static StrategyBLService strategyBLSkel;

	static UsermainBLService usermainBLSkel;

	public static AdminMainController adminMainController;

	public static ClientMainController clientMainController;

	public static HotelierMainController hotelierMainController;

	public static MarketerMainController marketerMainController;

	static public Scene loginScene;

	static public Scene signUpScene;

	static public Scene adminMainScene = null;

	/**
	 * 运行客户端程序.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HMSClient client = new HMSClient();
		client.init();
		try {
			DistrictData.updateData(searchHotelBLSkel.getDistricts());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Application.launch(ClientApp.class, args);
	}

	/**
	 * RMI服务初始化.
	 * 
	 * @return
	 */
	public ResultMessage init() {

		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

			createOrderBLSkel = (CreateOrderBLService) registry
					.lookup("createOrder");
			hotelInfoBLSkel = (HotelInfoBLService) registry.lookup("hotelInfo");
			manageOrderBLSkel = (ManageOrderBLService) registry
					.lookup("manageOrder");
			manageRoomBLSkel = (ManageRoomBLService) registry
					.lookup("manageRoom");
			manageUserBLSkel = (ManageUserBLService) registry
					.lookup("manageUser");
			searchHotelBLSkel = (SearchHotelBLService) registry
					.lookup("searchHotel");
			strategyBLSkel = (StrategyBLService) registry.lookup("strategy");
			usermainBLSkel = (UsermainBLService) registry.lookup("userMain");

		} catch (RemoteException e) {
			// 远程连接失败
			e.printStackTrace();
			return new ResultMessage(false);
		} catch (NotBoundException e) {
			// 没有找到相应的RMI服务
			e.printStackTrace();
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}

	/**
	 * 读取本地的数据文件.
	 * 
	 * @return
	 */
	public ResultMessage loadData() {
		// 待完整实现
		// MemberLevelData
		try {
			DistrictData
					.updateData(HMSClient.getSearchHotelBL().getDistricts());
		} catch (RemoteException e) {
			// 网络连接错误
			e.printStackTrace();
		}
		return new ResultMessage(true);
	}

	/**
	 * 设置客户端的窗口.
	 * 
	 * @param primaryStage
	 */
	public static void setMainStage(Stage primaryStage) {
		mainStage = primaryStage;
	}

	/**
	 * 先加载fxml文件，再切换客户端窗口的内容.
	 * 
	 * @param resourcePath
	 *            fxml文件资源的路径
	 * @return
	 */
	public static ResultMessage switchScene(String resourcePath) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClientApp.class.getResource(resourcePath));
			mainStage.setScene(new Scene(loader.load(), 1280, 800));
			return new ResultMessage(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(false);
		}
	}

	/**
	 * 切换客户端窗口的内容.
	 * 
	 * @param scene
	 */
	public static void showScene(Scene scene) {
		mainStage.setScene(scene);
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
