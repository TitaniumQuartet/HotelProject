package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.customnode.HotelBriefPane;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.UserVO;

public class HomePageController implements Initializable{

    @FXML
    private AnchorPane mianPane;

    @FXML
    private ComboBox<String> districtBox;

    @FXML
    private Button searchHotelButton;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private GridPane hotelGridPane;

    @FXML
    private GridPane orderGridPane;
    
    private int cityID = -1;

    @FXML
    void onSearchHotel(ActionEvent event) {

    }
    
    void setContent(){
    	try {
			List<HotelBriefVO> hotelList = HMSClient.getSearchHotelBL().recommend(LoginController.getCurrentUser().userID);
			hotelGridPane.add(new HotelBriefPane(hotelList.get(0)), 0, 0);
			hotelGridPane.add(new HotelBriefPane(hotelList.get(1)), 0, 1);
		} catch (RemoteException e) {
			//处理网络异常
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		districtBox.setDisable(true);
		cityBox.getItems().addAll(DistrictData.getCityMap().values());
		cityBox.getSelectionModel().clearSelection();
		cityID = -1;
	}

}
