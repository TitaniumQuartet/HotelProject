package tiquartet.ClientModule.ui.clientui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
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
	private FlowPane bottomBarPane;

	@FXML
	private Button logoutButton;

	@FXML
	private Button modifyPasswordButton;

	@FXML
	private Button personalInfoButton;

	@FXML
	private Button registerMemberButton;

	@FXML
	private Button toCreditRecordButton;

	@FXML
	private AnchorPane topBarPane;

	@FXML
	private ImageView topRowView;

	@FXML
	private FlowPane mainFlowPane;

	public Parent modifyPasswordPane = null;

	public Parent homePage = null;

	public Parent searchHotel;
	SearchHotelController searchHotelController;

	public Parent myHotelList = null;

	public Parent myOrderList = null;
	OrderListController orderListController;

	public Parent hotelDetails = null;
	HotelPageController hotelPageController;

	public Parent writeReview;
	ReviewDialogController reviewDialogController;

	public Parent createOrder;
	CreateOrderController createOrderController;

	public Parent memberSignUp;
	MemberSignUpController memberSignUpController;

	public Parent clientInfo;
	ClientInfoController clientInfoController;

	public Parent creditRecord;
	CreditRecordController creditRecordController;

	ArrayList<Button> bottomButtons;

	@FXML
	void onLogout(ActionEvent event) {
		try {
			HMSClient.getUserMainBL()
					.logout(LoginController.getCurrentUser().userID);
			HMSClient.switchScene("/fxml/usermainui/login.fxml");
			HMSClient.clientMainController = null;
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
		showClientInfo((Parent) mainFlowPane.getChildren().get(0));
	}

	@FXML
	void toRegisterMember(ActionEvent event) {
		showMemberSignUp((Parent) mainFlowPane.getChildren().get(0));
	}

	@FXML
	void toCreditRecord(ActionEvent event) {
		showCreditRecord((Parent) mainFlowPane.getChildren().get(0));
	}

	public void showHomePage() {
		if (homePage == null) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/fxml/clientui/homePage.fxml"));
				homePage = loader.load();
				HomePageController homePageController = loader.getController();
				homePageController.clientMainController = this;
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

	public void showSearchHotel(int districtID) {
		if (searchHotel == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/searchHotel.fxml"));
				searchHotel = loader.load();
				searchHotelController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		searchHotelController.enter(districtID);
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(searchHotel);
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
				reviewDialogController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		Stage dialog = new Stage(StageStyle.UNDECORATED);
		dialog.setScene(new Scene(writeReview, 396, 400));
		reviewDialogController.reviewDialog = dialog;
		reviewDialogController.hotelID = hotelID;
		dialog.show();
	}

	public void showCreateOrder(long orderID, LocalDate checkInDate,
			Parent previousPage) {
		if (createOrder == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/createOrder.fxml"));
				createOrder = loader.load();
				createOrderController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		createOrderController.enter(orderID, checkInDate, previousPage);
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(createOrder);
	}

	public void showMemberSignUp(Parent previous) {
		if (memberSignUp == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/memberSignUp.fxml"));
				memberSignUp = loader.load();
				memberSignUpController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		memberSignUpController.enter(previous);
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(memberSignUp);
	}

	public void showClientInfo(Parent previous) {
		if (clientInfo == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/clientInfo.fxml"));
				clientInfo = loader.load();
				clientInfoController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		clientInfoController.enter(previous);
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(clientInfo);
	}

	public void showCreditRecord(Parent previous) {
		if (creditRecord == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/creditRecord.fxml"));
				creditRecord = loader.load();
				creditRecordController = loader.getController();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		}
		creditRecordController.enter(previous);
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(creditRecord);
	}

	public void showSimply(Parent page) {
		mainFlowPane.getChildren().clear();
		mainFlowPane.getChildren().add(page);
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
