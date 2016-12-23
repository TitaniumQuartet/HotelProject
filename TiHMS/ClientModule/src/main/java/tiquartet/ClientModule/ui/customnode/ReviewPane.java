package tiquartet.ClientModule.ui.customnode;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.ReviewVO;

public class ReviewPane extends FlowPane {
	ReviewVO reviewVO;

	public ReviewPane(ReviewVO dataVO) {
		super();
		reviewVO = dataVO;
		Label usernameLabel = new Label(reviewVO.userName);
		usernameLabel.setFont(new Font(13));
		usernameLabel.setPrefWidth(110);
		Label rateLabel = new Label("评分：" + reviewVO.score);
		rateLabel.setFont(new Font(14));
		rateLabel.setPrefWidth(59);
		Label timeLabel = new Label(reviewVO.time);
		rateLabel.setFont(new Font(12));
		Label textLabel = new Label(reviewVO.review);
		textLabel.setPrefWidth(605);
		textLabel.setWrapText(true);

		setPrefWidth(625);
		setPrefHeight(USE_COMPUTED_SIZE);
		setPadding(new Insets(10));
		setHgap(20);
		setVgap(10);
	}
}
