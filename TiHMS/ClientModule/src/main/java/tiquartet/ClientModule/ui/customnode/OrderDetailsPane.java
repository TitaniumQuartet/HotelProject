package tiquartet.ClientModule.ui.customnode;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.OrderVO;

public class OrderDetailsPane extends FlowPane {

	public OrderDetailsPane(OrderVO orderVO) {
		super();
		if (orderVO == null)
			return;
		Font font18 = new Font(18);
		Font font12 = new Font(12);
		Label orderIDLabel = new Label(
				"订单号：" + String.format("%012d", orderVO.orderId) + "    ");
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
				+ String.format("%.2d", orderVO.price));
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
		setVgap(6);
		setHgap(20);
		setMargin(orderIDLabel, new Insets(10));
		setMargin(hotelLink, new Insets(10));
		setMargin(statusLabel, new Insets(10));

	}
}
