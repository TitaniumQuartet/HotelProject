package tiquartet.ClientModule.ui.customnode;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.OrderVO;

public class OrderPane extends AnchorPane {

	public OrderPane(OrderVO orderVO) {
		super();
		if(orderVO == null) return;
		Font font18 = new Font(18);
		Hyperlink orderIDLink = new Hyperlink(
				"订单号：" + String.format("%012d", orderVO.orderId));
		orderIDLink.setFont(font18);
		//应当改成酒店名称
		Hyperlink hotelLink = new Hyperlink(String.valueOf(orderVO.hotelId));
		hotelLink.setFont(font18);
		// 应当改成房间类型名称
		Label infoLabel = new Label("<html><body>" + "客房" + " "
				+ String.valueOf(orderVO.numberOfRoom) + "间    "
				+ orderVO.startTime.substring(0, 10) + "入住，"
				+ orderVO.leaveTime.substring(0, 10) + "离店" + "<br />实际入住："
				+ orderVO.guestrealName + "<br />于" + orderVO.orderTime
				+ "下单</body></html>");
		infoLabel.setFont(font18);
		Label priceLabel = new Label(String.format("%.2d", orderVO.price) + "  "
				+ orderVO.orderStatus.name());
		priceLabel.setFont(new Font(24));

		getChildren().addAll(orderIDLink, hotelLink, infoLabel, priceLabel);
		setLeftAnchor(orderIDLink, 25.0);
		setTopAnchor(orderIDLink, 20.0);
		setRightAnchor(hotelLink, 25.0);
		setTopAnchor(hotelLink, 20.0);
		setLeftAnchor(infoLabel, 25.0);
		setTopAnchor(infoLabel, 60.0);
		setRightAnchor(priceLabel, 25.0);
		setBottomAnchor(priceLabel, 25.0);
	}
}
