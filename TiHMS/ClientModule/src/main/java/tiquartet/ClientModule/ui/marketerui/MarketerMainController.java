package tiquartet.ClientModule.ui.marketerui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
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
import tiquartet.ClientModule.ui.hotelierui.HotelStrategyController;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.OrderFilterVO;

public class MarketerMainController implements Initializable {

	@FXML
	private FlowPane bottomBarPane;

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
	Parent strategy;
	Parent homePage;
	Parent abnormal;
	Parent unexecuted;

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
	void toStrategy(ActionEvent event) {
		if (strategy == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/hotelierui/hotelStrategy.fxml"));
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

	public void showHomePage() {
		if (homePage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/marketerui/homePage.fxml"));
				homePage = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(homePage);
	}

	public void showAbnormal(OrderFilterVO filterVO) {
		if (abnormal == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/marketerui/abnormal.fxml"));
				abnormal = loader.load();
				AbnormalController abnormalController = loader.getController();
				abnormalController.enter(filterVO);
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(abnormal);
	}

	public void showUnexecuted() {
		if (unexecuted == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/marketerui/unexecuted.fxml"));
				unexecuted = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(unexecuted);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showHomePage();
	}

}
