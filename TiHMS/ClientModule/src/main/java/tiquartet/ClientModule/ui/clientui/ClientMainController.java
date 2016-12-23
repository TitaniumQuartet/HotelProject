package tiquartet.ClientModule.ui.clientui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.MemberType;

public class ClientMainController implements Initializable {

	@FXML
	private AnchorPane bottomBarPane;

	@FXML
	private Button logoutButton;

	@FXML
	private Button modifyPasswordButton;

	@FXML
	private Button personalInfoButton;

	@FXML
	private Button registerMemberButton;

	@FXML
	private Button backButton1;

	@FXML
	private Button backButton2;

	@FXML
	private AnchorPane topBarPane;

	@FXML
	private ImageView topRowView;

	@FXML
	private FlowPane mainFlowPane;

	private Parent modifyPasswordPane = null;

	private Parent homePage = null;

	private Parent myHotelList = null;

	private Parent myOrderList = null;

	OrderListController orderListController;

	private Parent hotelDetails = null;

	HotelPageController hotelPageController;

	private Parent writeReview;

	ReviewDialogController ReviewDialogController;

	@FXML
	void goBack1(ActionEvent event) {

	}

	@FXML
	void goBack2(ActionEvent event) {

	}

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
		if (modifyPasswordPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/adminui/modifyPassword.fxml"));
				modifyPasswordPane = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(modifyPasswordPane);
	}

	@FXML
	void toPersonalInfo(ActionEvent event) {

	}

	@FXML
	void toRegisterMember(ActionEvent event) {

	}

	public void showHomePage() {
		if (homePage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/fxml/clientui/homePage.fxml"));
				homePage = loader.load();
				HomePageController homePageController = loader.getController();
				// homePageController.setContent();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(homePage);
	}

	public void showMyHotelList() {
		if (myHotelList == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/hotelList.fxml"));
				myHotelList = loader.load();
				HotelListController hotelListController = loader
						.getController();
				hotelListController.clientMainController = this;
				hotelListController.setHotelList();
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(myHotelList);
				// 设置返回按钮

			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	public void showMyOrders() {
		if (myOrderList == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/orderList.fxml"));
				myOrderList = loader.load();
				orderListController = loader.getController();
				orderListController.clientMainController = this;
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		orderListController.enter();
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(myHotelList);
		// 设置返回按钮

	}

	public void showHotelDetails(int hotelID) {
		if (hotelDetails == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/hotelPage.fxml"));
				hotelDetails = loader.load();
				hotelPageController = loader.getController();
				hotelPageController.clientMainController = this;
				mainFlowPane.getChildren().clear();
				mainFlowPane.getChildren().add(hotelDetails);
				// 设置返回按钮

			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
	}

	public void showReviewDialog(int hotelID) {
		if (writeReview == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/reviewDialog.fxml"));
				writeReview = loader.load();
				ReviewDialogController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		Stage dialog = new Stage(StageStyle.UNDECORATED);
		dialog.setScene(new Scene(writeReview, 396, 400));
		ReviewDialogController.reviewDialog = dialog;
		ReviewDialogController.hotelID = hotelID;
		dialog.show();
	}

	public void renewMemberButton() {
		registerMemberButton.setVisible(
				LoginController.getCurrentUser().memberType == MemberType.非会员);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// renewMemberButton();
		showHomePage();
	}

}
