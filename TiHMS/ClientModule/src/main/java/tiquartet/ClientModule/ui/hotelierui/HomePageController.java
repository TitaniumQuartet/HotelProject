package tiquartet.ClientModule.ui.hotelierui;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import tiquartet.ClientModule.ui.customnode.OrderDetailsPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderVO;

public class HomePageController implements Initializable {

	@FXML
	private AnchorPane backAnchorPane;

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
	private DatePicker outDateBox;

	@FXML
	private Button getOfflineRoom;

	@FXML
	private Button allOrder;

	@FXML
	private Button unexecutedOrder;

	@FXML
	private Button abnormalOrder;

	@FXML
	private Button executedOrder;

	@FXML
	private Button canceledOrder;

	@FXML
	private Button offlineOrder;

	Image smallSearchImage;
	Image bigSearchImage;

	@FXML
	void backPaneClicked(MouseEvent event) {
		backAnchorPane.requestFocus();
	}

	@FXML
	void buttonEntered(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.LIGHTSTEELBLUE, null, null)));
	}

	@FXML
	void buttonExited(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.STEELBLUE, null, null)));
	}

	@FXML
	void onAbnormalOrder(ActionEvent event) {
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.异常订单;
	}

	@FXML
	void onAllOrder(ActionEvent event) {
		HMSClient.hotelierMainController.showOrderpage(null);
	}

	@FXML
	void onCanceledOrder(ActionEvent event) {
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.已撤销订单;
		HMSClient.hotelierMainController.showOrderpage(filterVO);
	}

	@FXML
	void onExecutedOrder(ActionEvent event) {
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.已执行订单;
		HMSClient.hotelierMainController.showOrderpage(filterVO);
	}

	@FXML
	void onGetOfflineRoom(ActionEvent event) {
		HMSClient.hotelierMainController.showOffline(outDateBox.getValue());
	}

	@FXML
	void onOfflineOrder(ActionEvent event) {
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.线下已执行订单;
		HMSClient.hotelierMainController.showOrderpage(filterVO);
	}

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
		filterVO.orderState = null;
		filterVO.userName = field2.getText();
		filterVO.clientRealName = field3.getText();
		filterVO.guestRealName = field4.getText();
		HMSClient.hotelierMainController.showOrderpage(filterVO);
	}

	@FXML
	void onUnexecutedOrder(ActionEvent event) {
		OrderFilterVO filterVO = new OrderFilterVO();
		filterVO.orderState = OrderStatus.未执行订单;
		HMSClient.hotelierMainController.showOrderpage(filterVO);
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
