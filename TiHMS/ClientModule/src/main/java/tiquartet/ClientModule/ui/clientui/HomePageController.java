package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.customnode.HotelBriefPane;
import tiquartet.ClientModule.ui.customnode.OrderPane;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderVO;

public class HomePageController implements Initializable{

    @FXML
    private AnchorPane mianPane;

    @FXML
    private ChoiceBox<String> districtBox;

    @FXML
    private Button searchHotelButton;

    @FXML
    private ChoiceBox<String> cityBox;

    @FXML
    private GridPane hotelGridPane;

    @FXML
    private GridPane orderGridPane;
    
    @FXML
    private Button myHotelsButton;
    
    @FXML
    private Button myOrdersButton;
    
    private int cityID = -1;
    
    public ClientMainController clientMainController;

    @FXML
    void onSearchHotel(ActionEvent event) {

    }
    
    @FXML
    void toMyHotels(ActionEvent event) {

    }

    @FXML
    void toMyOrders(ActionEvent event) {

    }
    
    public void setContent(){
    	try {
			List<HotelBriefVO> hotelList = HMSClient.getSearchHotelBL().recommend(LoginController.getCurrentUser().userID);
			for(int i=0;i<2;i++) hotelGridPane.add(new HotelBriefPane(hotelList.get(i)), 0, i);
			
			OrderFilterVO orderFilter = new OrderFilterVO();
			orderFilter.userName = LoginController.getCurrentUser().userName;
			List<OrderVO> orderList = HMSClient.getManageOrderBL().orderHistory(orderFilter, OrderSort.生成日期降序, 1, 3);
			for(int i=0;i<3;i++) orderGridPane.add(new OrderPane(orderList.get(i)), 0, i);
		} catch (RemoteException e) {
			//处理网络异常
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		districtBox.setDisable(true);
		cityBox.getItems().addAll(DistrictData.getCityMap().values());
		cityBox.getSelectionModel().select(0);
		cityID = -1;
		
	}

}
