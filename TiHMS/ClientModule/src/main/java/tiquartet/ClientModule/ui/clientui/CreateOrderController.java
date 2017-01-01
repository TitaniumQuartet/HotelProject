package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;

public class CreateOrderController implements Initializable {

	@FXML
	private Button cancelOrder;

	@FXML
	private ChoiceBox<String> latestTimeBox;

	@FXML
	private TextField guestNumBox;

	@FXML
	private ChoiceBox<String> childBox;

	@FXML
	private FlowPane guestNameFlowPane;

	@FXML
	private Button nextStepButton;

	@FXML
	private Label priceLabel;

	@FXML
	private Label strategyIntroLabel;

	@FXML
	private Button confirmButton;

	@FXML
	private Label confirmPromptLabel;

	@FXML
	private Button modifyButton;

	LocalDate inDate;
	long orderID;
	OrderStrategyVO orderStrategyVO;

	Parent previous;

	@FXML
	void onCancelOrder(ActionEvent event) {
		try {
			ResultMessage message = HMSClient.getCreateOrderBL()
					.cancelPreOrder(LoginController.getCurrentUser().userID);
			if (message.result) {
				Alert cancel = new Alert(AlertType.INFORMATION, "成功撤销本次预订！");
				cancel.show();
			} else {
				Alert cancel = new Alert(AlertType.INFORMATION, "预订撤销失败");
				cancel.show();
				HMSClient.clientMainController.showSimply(previous);
			}
		} catch (RemoteException e) {
			// 网络异常处理.
			e.printStackTrace();
		}
	}

	@FXML
	void onConfirmOrder(ActionEvent event) {
		try {
			OrderInfoVO orderInfo = new OrderInfoVO();
			String guest = "";
			for (Node field : guestNameFlowPane.getChildren())
				guest = guest + "；" + ((TextField) field).getText();
			guest = guest.substring(1);
			orderInfo.guestRealName = guest;
			orderInfo.kids = childBox.getSelectionModel().getSelectedIndex();
			orderInfo.latestTime = latestTimeBox.getSelectionModel()
					.getSelectedItem();
			orderInfo.numOfGuest = Integer.parseInt(guestNumBox.getText());
			orderInfo.orderID = orderID;
			orderInfo.strategyID = orderStrategyVO.strategyID;
			ResultMessage message = HMSClient.getCreateOrderBL()
					.confirm(orderInfo);
			if (message.result) {
				Alert cancel = new Alert(AlertType.INFORMATION,
						"订单" + orderID + "提交成功！");
				cancel.show();
				HMSClient.clientMainController.showSimply(previous);
			} else {
				Alert cancel = new Alert(AlertType.ERROR, "订单提交失败");
				cancel.show();
			}
		} catch (RemoteException e) {
			// 网络异常处理
		}
	}

	@FXML
	void onNextStep(ActionEvent event) {
		latestTimeBox.setDisable(true);
		guestNumBox.setDisable(true);
		childBox.setDisable(true);
		guestNameFlowPane.setDisable(true);
		nextStepButton.setVisible(false);
		confirmButton.setVisible(true);
		modifyButton.setVisible(true);
		confirmPromptLabel.setVisible(true);
	}

	@FXML
	void onModifyClicked(ActionEvent event) {
		latestTimeBox.setDisable(false);
		guestNumBox.setDisable(false);
		childBox.setDisable(false);
		guestNameFlowPane.setDisable(false);
		nextStepButton.setVisible(true);
		confirmButton.setVisible(false);
		modifyButton.setVisible(false);
		confirmPromptLabel.setVisible(false);
	}

	/**
	 * 每次切换至完成订单界面时调用.
	 * 
	 * @param orderID
	 */
	public void enter(long preOrderID, LocalDate checkInDate,
			Parent previousPage) {
		childBox.getSelectionModel().select(0);
		orderID = preOrderID;
		inDate = checkInDate;
		previous = previousPage;
		nextStepButton.setDisable(false);
		confirmButton.setVisible(false);
		confirmPromptLabel.setVisible(false);
		latestTimeBox.setDisable(false);
		latestTimeBox.getSelectionModel().select(2);
		guestNumBox.setText("1");
		guestNumBox.setDisable(false);
		childBox.setDisable(false);
		childBox.getSelectionModel().select(0);
		TextField field = (TextField) guestNameFlowPane.getChildren().get(0);
		field.setText("");
		field.setPromptText("客人1");

		try {
			orderStrategyVO = HMSClient.getCreateOrderBL()
					.getStrategy(LoginController.getCurrentUser().userID);
			priceLabel.setText(
					"￥" + String.format("%.2f", orderStrategyVO.orderPrice));
			strategyIntroLabel.setText(orderStrategyVO.strategyIntroduce);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String date1Prefix = inDate.toString() + " ";
		String date2Prefix = inDate.plusDays(1).toString() + " ";
		latestTimeBox.getItems().addAll(date1Prefix + "12:00",
				date1Prefix + "16:00", date1Prefix + "20:00",
				date2Prefix + "00:00", date2Prefix + "04:00");
		childBox.getItems().addAll("无", "有");
		guestNumBox.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (!newValue.isEmpty() && !newValue.matches("\\d+"))
					guestNumBox.setText("");
				else {
					int guestNum = Integer.parseInt(newValue);
					if (guestNum < 1 || guestNum > 100)
						guestNumBox.setText("");
					while (guestNameFlowPane.getChildren().size() < guestNum) {
						TextField field = new TextField();
						field.setPrefWidth(80);
						field.setFont(new Font(14));
						field.setPromptText("客人"
								+ (guestNameFlowPane.getChildren().size() + 1));
						guestNameFlowPane.getChildren().add(field);
					}
					while (guestNameFlowPane.getChildren().size() > guestNum) {
						guestNameFlowPane.getChildren().remove(
								guestNameFlowPane.getChildren().size() - 1);
					}
				}
			}
		});
	}

}
