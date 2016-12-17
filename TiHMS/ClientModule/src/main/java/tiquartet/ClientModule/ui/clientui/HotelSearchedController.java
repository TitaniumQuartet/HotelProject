package tiquartet.ClientModule.ui.clientui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HotelSearchedController {

	@FXML
	private ImageView hotelImage;

	@FXML
	private Label hotelNameLabel;

	@FXML
	private ImageView starImage;

	@FXML
	private Label rateLabel;

	@FXML
	private Label introLabel;

	@FXML
	private Label orderNumLabel;

	@FXML
	private ScrollPane scroll;

	@FXML
	private HBox roomTypeHBox;

	@FXML
	private Label contactLabel;

	@FXML
	private TextField contactField;

	@FXML
	private Button createOrderLabel;

	@FXML
	private ChoiceBox<?> roomNumBox;

	@FXML
	void onCreateOrder(ActionEvent event) {

	}

}
