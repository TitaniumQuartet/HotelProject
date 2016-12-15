package tiquartet.ClientModule.ui.customnode;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tiquartet.CommonModule.vo.HotelBriefVO;

public class HotelBriefPane extends AnchorPane{
	
	private HotelBriefVO hotelBriefVO;
	
	public HotelBriefPane(HotelBriefVO hotelBriefVO){
		super();
		this.hotelBriefVO = hotelBriefVO;
		Hyperlink hotelNameLabel = new Hyperlink(hotelBriefVO.hotelName);
		hotelNameLabel.setFont(new Font(24));
		Label introLabel = new Label("<html><body>"+hotelBriefVO.introduction+"<br />我有"+String.valueOf(hotelBriefVO.numOfAllOrder)+"笔订单，已执行"+String.valueOf(hotelBriefVO.numOfAllOrder)+"笔</body></html>");
		introLabel.setFont(new Font(18));
		Label rateLabel = new Label(String.format(".1%f", hotelBriefVO.averageGrade)+"分");
		rateLabel.setFont(new Font("System Italic", 24));
		ImageView imageView = new ImageView();
		imageView.setFitHeight(239.0);
		imageView.setFitWidth(239.0);
		
		getChildren().addAll(hotelNameLabel, introLabel, rateLabel, imageView);
		setLeftAnchor(hotelNameLabel, 260.0);
		setTopAnchor(hotelNameLabel, 26.0);
		setLeftAnchor(rateLabel, 260.0);
		setTopAnchor(rateLabel, 64.0);
		setLeftAnchor(introLabel, 260.0);
		setTopAnchor(introLabel, 120.0);
		setLeftAnchor(imageView, 5.0);
		setTopAnchor(imageView, 5.0);
	}
}
