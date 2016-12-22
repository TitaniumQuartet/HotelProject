package tiquartet.ClientModule.ui.customnode;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.RoomTypeVO;

public class RoomTypePane extends FlowPane {
	RoomTypeVO roomTypeVO;
	public RadioButton button;

	public RoomTypePane(RoomTypeVO typeVO) {
		roomTypeVO = typeVO;
		button = new RadioButton(roomTypeVO.roomType);
		button.setFont(new Font(18));
		Label priceLabel = new Label("￥" + roomTypeVO.price);
		priceLabel.setFont(new Font(24));
		Label introLabel = new Label(
				roomTypeVO.typeIntroduction + "\n剩余" + roomTypeVO.number + "间");
		introLabel.setPrefWidth(180);
		introLabel.setPrefHeight(120);
		introLabel.setFont(new Font(14));
		introLabel.setWrapText(true);

		setMinWidth(200);
		setMaxWidth(200);
		setOrientation(Orientation.VERTICAL);
		setAlignment(Pos.CENTER);
		setColumnHalignment(HPos.CENTER);
		getChildren().addAll(button, priceLabel, introLabel);
	}
}
