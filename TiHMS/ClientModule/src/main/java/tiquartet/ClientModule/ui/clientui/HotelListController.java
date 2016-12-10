package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.customnode.HotelBriefPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.HotelBriefVO;

public class HotelListController implements Initializable{

    @FXML
    private ChoiceBox<Integer> pageNumBox;

    @FXML
    private Label titleLabel;

    @FXML
    private Button lastPageButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private GridPane hotelGridPane;
    
    private List<Integer> myHotelList = null;
    
    public ClientMainController clientMainController = null;
    
    public void setHotelList(){
    	try {
			myHotelList = HMSClient.getManageOrderBL().orderedHotelID(LoginController.getCurrentUser().userID);
			int size = myHotelList.size();
			for(int i=1;i<=size/4;i++) pageNumBox.getItems().add(i);
			pageNumBox.getSelectionModel().select(1);
			lastPageButton.setDisable(true);
			if(size<5) nextPageButton.setDisable(true);
			
		} catch (RemoteException e) {
			//网络连接错误
			e.printStackTrace();
		}
    }

    @FXML
    void onLastPage(ActionEvent event) {
    	pageNumBox.getSelectionModel().select(pageNumBox.getSelectionModel().getSelectedIndex()-1);
    }

    @FXML
    void onNextPage(ActionEvent event) {
    	pageNumBox.getSelectionModel().select(pageNumBox.getSelectionModel().getSelectedIndex()+1);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*HotelSort[] sortMethods = HotelSort.values();
		String[] names = new String[sortMethods.length];
		for(int i=0;i<sortMethods.length;i++) names[i] = sortMethods[i].name();
		sortBox.getItems().addAll(Arrays.asList(names));
		sortBox.getSelectionModel().clearSelection();*/
		pageNumBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if(newValue!=null){
					try {
						int page = (Integer) newValue;
						int userID = LoginController.getCurrentUser().userID;
						ArrayList<HotelBriefVO> hotels = new ArrayList<>();
						for(int i=page*4-3;i<=page*4&&i<=myHotelList.size();i++){
							hotels.add(HMSClient.getHotelInfoBL().getHotelBrief(myHotelList.get(i), userID));
						}
						hotelGridPane.add(new HotelBriefPane(hotels.get(0)), 0, 0);
						hotelGridPane.add(new HotelBriefPane(hotels.get(1)), 1, 0);
						hotelGridPane.add(new HotelBriefPane(hotels.get(2)), 0, 1);
						hotelGridPane.add(new HotelBriefPane(hotels.get(3)), 1, 1);
						nextPageButton.setDisable(page*4>=myHotelList.size());
						lastPageButton.setDisable(page==1);
					} catch (RemoteException e) {
						//网络连接异常
						e.printStackTrace();
					}
				}
			}
		});
	}
    
}
