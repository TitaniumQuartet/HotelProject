package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.Refreshable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tiquartet.CommonModule.vo.HotelFilterVO;

public class SearchHotelController implements Initializable{

    @FXML
    private ChoiceBox<String> pageNumBox;

    @FXML
    private Button lastPageButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private ChoiceBox<String> starBox;

    @FXML
    private ChoiceBox<String> priceBox;

    @FXML
    private ChoiceBox<String> rateBox;

    @FXML
    private TextField hotelNameField;

    @FXML
    private DatePicker inDateBox;

    @FXML
    private DatePicker outDateBox;

    @FXML
    private MenuItem priceDescend;

    @FXML
    private MenuItem priceAscend;

    @FXML
    private MenuItem rateDescend;

    @FXML
    private MenuItem rateAscend;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    public HotelFilterVO filter;

    @FXML
    void onLastPage(ActionEvent event) {

    }

    @FXML
    void onNextPage(ActionEvent event) {

    }

    @FXML
    void onPriceAscend(ActionEvent event) {

    }

    @FXML
    void onPriceDescend(ActionEvent event) {

    }

    @FXML
    void onRateAscend(ActionEvent event) {

    }

    @FXML
    void onRateDescend(ActionEvent event) {

    }
    
    public void refresh(){
    	starBox.getSelectionModel().select(0);
    	rateBox.getSelectionModel().select(0);
    	priceBox.getSelectionModel().select(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		starBox.getItems().addAll("星级不限","五星级","四星级","三星以下");
		rateBox.getItems().addAll("评分不限","评分8-10","评分6-8","评分6以下");
		priceBox.getItems().addAll("价格不限","￥500以上","￥300-500","￥200-300","￥200以下");
	}

}
