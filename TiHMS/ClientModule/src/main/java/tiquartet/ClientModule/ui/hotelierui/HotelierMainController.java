package tiquartet.ClientModule.ui.hotelierui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.OrderFilterVO;

public class HotelierMainController implements Initializable {

	@FXML
	private FlowPane bottomBarPane;

	@FXML
	private Button roomButton;

	@FXML
	private Button hotelInfoButton;

	@FXML
	private Button toStrategyButton;

	@FXML
	private Button modifyPasswordButton;

	@FXML
	private Button logoutButton;

	@FXML
	private AnchorPane topBarPane;

	@FXML
	private ImageView topRowView;

	@FXML
	private FlowPane mainFlowPane;

	Parent modifyPassword;
	Parent homePage;
	Parent hotelInfoPage;
	Parent roomTypePage;
	Parent orderPage;
	Parent offline;
	Parent strategy;

	public HotelBriefVO hotelInfo;

	@FXML
	void onLogout(ActionEvent event) {
		try {
			HMSClient.getUserMainBL()
					.logout(LoginController.getCurrentUser().userID);
			HMSClient.switchScene("/fxml/usermainui/login.fxml");
		} catch (RemoteException e) {
			// 网络连接错误
			e.printStackTrace();
		}
	}

	@FXML
	void onModifyPassword(ActionEvent event) {
		if (modifyPassword == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/adminui/modifyPassword.fxml"));
				modifyPassword = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(modifyPassword);
	}

	@FXML
	void toHotelInfo(ActionEvent event) {
		if (hotelInfoPage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/fxml/adminui/hotelInfo.fxml"));
				hotelInfoPage = loader.load();
				HotelInfoController hotelInfoController = loader
						.getController();
				hotelInfoController
						.enter((Parent) mainFlowPane.getChildren().get(0));
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(hotelInfoPage);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	@FXML
	void toRoom(ActionEvent event) {
		if (roomTypePage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/fxml/adminui/roomType.fxml"));
				roomTypePage = loader.load();
				RoomTypeController roomTypeController = loader.getController();
				roomTypeController
						.enter((Parent) mainFlowPane.getChildren().get(0));
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(roomTypePage);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	@FXML
	void toStrategy(ActionEvent event) {
		if (strategy == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/adminui/hotelStrategy.fxml"));
				strategy = loader.load();
				HotelStrategyController hotelStrategyController = loader
						.getController();
				hotelStrategyController
						.enter((Parent) mainFlowPane.getChildren().get(0));
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(strategy);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	@FXML
	void bottomEntered(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.LIGHTSTEELBLUE, null, null)));
	}

	@FXML
	void bottomExited(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.STEELBLUE, null, null)));
	}

	public void showHomePage() {
		try {
			hotelInfo = HMSClient.getHotelInfoBL().getHotelBrief(
					LoginController.getCurrentUser().hotelID,
					LoginController.getCurrentUser().userID);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
		if (homePage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/hotelierui/homePage.fxml"));
				homePage = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(homePage);
	}

	public void showOrderpage(OrderFilterVO filterVO) {
		if (orderPage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/hotelierui/orderPage.fxml"));
				orderPage = loader.load();
				OrderPageController orderPageController = loader
						.getController();
				orderPageController.enter(filterVO);
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(orderPage);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	public void showOffline(LocalDate date) {
		if (offline == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/hotelierui/orderPage.fxml"));
				offline = loader.load();
				OfflineCheckInController offlineCheckInController = loader
						.getController();
				offlineCheckInController.enter(date);
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(offline);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	public void showSimply(Parent parent) {
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(parent);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
