package tiquartet.ClientModule.ui.customnode;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.OrderVO;

public class OrderBriefPane extends AnchorPane {

	public OrderBriefPane(OrderVO orderVO) {
		super();
		if(orderVO == null) return;
		Font font18 = new Font(18);
		Label orderIDLabel = new Label(
				"订单号：" + String.format("%012d", orderVO.orderId));
		orderIDLabel.setFont(font18);
		//应当改成酒店名称
		Hyperlink hotelLink = new Hyperlink(String.valueOf(orderVO.hotelName));
		hotelLink.setFont(font18);
		// 应当改成房间类型名称
		Label roomLabel = new Label(orderVO.roomTypeName + " "
				+ String.valueOf(orderVO.numberOfRoom) + "间    "
				+ orderVO.startTime.substring(0, 10) + "入住，"
				+ orderVO.leaveTime.substring(0, 10) + "离店");
		roomLabel.setFont(font18);
		Label guestLabel = new Label("实际入住："+ orderVO.guestrealName);
		guestLabel.setFont(font18);
		Label timeLabel = new Label("于" + orderVO.orderTime+ "下单");
		timeLabel.setFont(font18);
		Label priceLabel = new Label(String.format("%.2d", orderVO.price) + "  "
				+ orderVO.orderStatus.name());
		priceLabel.setFont(new Font(24));

		getChildren().addAll(orderIDLabel, hotelLink, roomLabel, priceLabel);
		setLeftAnchor(orderIDLabel, 25.0);
		setTopAnchor(orderIDLabel, 20.0);
		setRightAnchor(hotelLink, 25.0);
		setTopAnchor(hotelLink, 20.0);
		setLeftAnchor(roomLabel, 25.0);
		setTopAnchor(roomLabel, 60.0);
		setLeftAnchor(guestLabel, 25.0);
		setTopAnchor(guestLabel, 100.0);
		setLeftAnchor(timeLabel, 25.0);
		setTopAnchor(timeLabel, 140.0);
		setRightAnchor(priceLabel, 25.0);
		setBottomAnchor(priceLabel, 25.0);
	}
}
