package tiquartet.ClientModule.ui.customnode;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.OrderVO;

public class OrderDetailsPane extends FlowPane {

	public OrderDetailsPane(OrderVO orderVO) {
		super();
		if (orderVO == null)
			return;
		Font font18 = new Font(18);
		Font font12 = new Font(12);
		Label orderIDLabel = new Label(
				"订单号：" + String.format("%09d", orderVO.orderId) + "    ");
		orderIDLabel.setFont(font18);
		// 应当改成酒店名称
		Hyperlink hotelLink = new Hyperlink(orderVO.hotelName);
		hotelLink.setFont(font18);

		Label statusLabel = new Label(orderVO.orderStatus.name());
		orderIDLabel.setFont(font18);
		// 应当改成房间类型名称
		Label roomLabel = new Label(orderVO.roomTypeName + "，"
				+ String.valueOf(orderVO.numberOfRoom) + "间："
				+ String.join("，", orderVO.roomMap.values()) + "  " + "  总价：￥"
				+ String.format("%.2f", orderVO.price));
		roomLabel.setWrapText(true);
		roomLabel.setFont(font12);

		Label guestLabel = new Label(
				"实际入住：" + orderVO.guestrealName + (orderVO.child == 0
						? "无儿童"
						: (String.valueOf(orderVO.child) + "名儿童")));
		guestLabel.setWrapText(true);
		guestLabel.setFont(font12);

		Label dateLabel = new Label(
				"入住：" + orderVO.startTime + "离店：" + orderVO.leaveTime);
		roomLabel.setFont(font12);

		Label timeLabel = new Label(
				"执行期限：" + orderVO.latestTime + "，于" + orderVO.orderTime + "下单");
		timeLabel.setFont(font12);

		getChildren().addAll(orderIDLabel, hotelLink, statusLabel, roomLabel,
				guestLabel, dateLabel, timeLabel);
		if (LoginController.getCurrentUser().userType == UserType.客户
				&& orderVO.orderStatus == OrderStatus.未执行订单) {
			Button button = new Button("撤销");
			button.setTextFill(Color.WHITE);
			button.setBackground(new Background(
					new BackgroundFill(Color.STEELBLUE, null, null)));
			button.setFont(new Font(14));
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						ResultMessage message = HMSClient.getManageOrderBL()
								.clientCancel(orderVO.orderId);
						if (message.result) {
							Alert alert = new Alert(AlertType.INFORMATION,
									"撤销订单成功！");
							alert.show();
							getChildren().remove(button);
						} else {
							Alert alert = new Alert(AlertType.INFORMATION,
									"撤销订单失败");
							alert.show();
						}
					} catch (RemoteException e) {
						// 网络异常处理
						e.printStackTrace();
					}
				}
			});
			getChildren().add(button);
		} else if (LoginController.getCurrentUser().userType == UserType.酒店工作人员
				&& (orderVO.orderStatus == OrderStatus.未执行订单
						|| orderVO.orderStatus == OrderStatus.异常订单)) {
			Button button = new Button("已执行");
			button.setTextFill(Color.WHITE);
			button.setBackground(new Background(
					new BackgroundFill(Color.STEELBLUE, null, null)));
			button.setFont(new Font(14));
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						// 输入预计离开时间

						ResultMessage message = HMSClient.getManageOrderBL()
								.checkIn(orderVO.orderId, orderVO.leaveTime);
						if (message.result) {
							Alert alert = new Alert(AlertType.INFORMATION,
									"订单执行成功！");
							alert.show();
							getChildren().remove(button);
						} else {
							Alert alert = new Alert(AlertType.INFORMATION,
									"订单执行失败");
							alert.show();
						}
					} catch (RemoteException e) {
						// 网络异常处理
						e.printStackTrace();
					}
				}
			});
			getChildren().add(button);
		} else if (LoginController.getCurrentUser().userType == UserType.网站营销人员
				&& orderVO.orderStatus == OrderStatus.异常订单) {
			ChoiceBox<String> choiceBox = new ChoiceBox<>();
			choiceBox.getItems().addAll("恢复一半信用值", "恢复全部信用值");
			choiceBox.getSelectionModel().select(0);
			Button button = new Button("撤销");
			button.setTextFill(Color.WHITE);
			button.setBackground(new Background(
					new BackgroundFill(Color.STEELBLUE, null, null)));
			button.setFont(new Font(14));
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						// 输入预计离开时间

						ResultMessage message = HMSClient.getManageOrderBL()
								.marketerCancel(orderVO.orderId,
										CreditRestore.values()[choiceBox
												.getSelectionModel()
												.getSelectedIndex()]);
						if (message.result) {
							Alert alert = new Alert(AlertType.INFORMATION,
									"订单撤销成功！");
							alert.show();
							getChildren().remove(choiceBox);
							getChildren().remove(button);
						} else {
							Alert alert = new Alert(AlertType.INFORMATION,
									"订单撤销失败");
							alert.show();
						}
					} catch (RemoteException e) {
						// 网络异常处理
						e.printStackTrace();
					}
				}
			});
			getChildren().add(choiceBox);
			getChildren().add(button);
		}
		setVgap(6);
		setHgap(20);
		setMargin(orderIDLabel, new Insets(10));
		setMargin(hotelLink, new Insets(10));
		setMargin(statusLabel, new Insets(10));

	}
}
