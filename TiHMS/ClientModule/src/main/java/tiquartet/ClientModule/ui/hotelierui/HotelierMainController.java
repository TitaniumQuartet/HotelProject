package tiquartet.ClientModule.ui.hotelierui;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;

public class HotelierMainController implements Initializable {

	@FXML
	private FlowPane bottomBarPane;

	@FXML
	private Button registerMemberButton;

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

	@FXML
	void onLogout(ActionEvent event) {
		try {
			HMSClient.getUserMainBL()
					.logout(LoginController.getCurrentUser().userID);
			HMSClient.showScene(HMSClient.loginScene);
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

	}

	@FXML
	void toRegisterMember(ActionEvent event) {

	}

	@FXML
	void toStrategy(ActionEvent event) {

	}

	public void showHomePage() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showHomePage();
	}

}
