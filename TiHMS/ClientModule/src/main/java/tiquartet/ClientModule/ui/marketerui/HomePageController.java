package tiquartet.ClientModule.ui.marketerui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import tiquartet.ClientModule.ui.customnode.OrderDetailsPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.vo.UserVO;

public class HomePageController implements Initializable {

	@FXML
	private FlowPane searchPane1;

	@FXML
	private TextField field1;

	@FXML
	private FlowPane searchPane2;

	@FXML
	private TextField field2;

	@FXML
	private FlowPane searchPane3;

	@FXML
	private TextField field3;

	@FXML
	private FlowPane searchPane4;

	@FXML
	private TextField field4;

	@FXML
	private Button searchOrderButton;

	@FXML
	private Button unexecutedTodayButton;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField creditField;

	@FXML
	private Button creditButton;

	Image smallSearchImage;
	Image bigSearchImage;

	@FXML
	void onSearchOrder(ActionEvent event) {
		if (!field1.getText().isEmpty()) {
			FlowPane flowPane = new FlowPane();
			flowPane.setPrefHeight(620);
			flowPane.setPrefWidth(1280);
			flowPane.setAlignment(Pos.CENTER);
			try {
				OrderVO vo = HMSClient.getManageOrderBL()
						.getOrderByID(Long.parseLong(field1.getText()));
				if (vo == null) {
					Alert alert = new Alert(AlertType.ERROR, "订单号错误");
				}
				flowPane.getChildren().add(new OrderDetailsPane(vo));
				HMSClient.hotelierMainController.showSimply(flowPane);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR, "订单号错误");
				alert.show();
				e.printStackTrace();
			} catch (RemoteException e) {
				// 网络异常处理
				e.printStackTrace();
			}
		}
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.异常订单;
		filterVO.userName = field2.getText();
		filterVO.clientRealName = field3.getText();
		filterVO.guestRealName = field4.getText();
		HMSClient.marketerMainController.showAbnormal(filterVO);
	}

	@FXML
	void onCreditRecharge(ActionEvent event) {

		try {
			if (!usernameField.getText().isEmpty()) {
				UserVO userVO;
				userVO = HMSClient.getManageUserBL()
						.accurateSearch(usernameField.getText());
				if (userVO == null) {
					Alert alert = new Alert(AlertType.ERROR, "不存在的用户名");
					alert.show();
				}
				ResultMessage message = HMSClient.getManageUserBL()
						.creditRecharge(userVO.userID,
								Double.parseDouble(creditField.getText()));
				if (message.result) {
					Alert alert = new Alert(AlertType.INFORMATION,
							"已成功给用户" + userVO.userName + "," + userVO.realName
									+ "充值信用值");
					alert.show();
				} else {
					Alert alert = new Alert(AlertType.ERROR, "充值失败");
					alert.show();
				}
			}
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "充值额度填写错误");
			alert.show();
		}
	}

	@FXML
	void onUnexecutedToday(ActionEvent event) {
		HMSClient.marketerMainController.showUnexecuted();
	}

	@FXML
	void searchButtonEntered(MouseEvent event) {
		searchOrderButton.setBackground(new Background(
				new BackgroundImage(bigSearchImage, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(BackgroundSize.AUTO,
								BackgroundSize.AUTO, false, false, true,
								false))));
	}

	@FXML
	void searchButtonExited(MouseEvent event) {
		searchOrderButton.setBackground(
				new Background(new BackgroundImage(smallSearchImage,
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.CENTER,
						new BackgroundSize(BackgroundSize.AUTO,
								BackgroundSize.AUTO, false, false, true,
								false))));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		smallSearchImage = new Image(getClass()
				.getResourceAsStream("/image/hotelierui/search1.png"));
	}
}
