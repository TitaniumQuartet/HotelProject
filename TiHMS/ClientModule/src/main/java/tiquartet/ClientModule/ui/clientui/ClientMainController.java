package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.CommonModule.vo.UserVO;

public class ClientMainController implements Initializable{

    @FXML
    private AnchorPane bottomBarPane;

    @FXML
    private Button logoutButton;

    @FXML
    private Button modifyPasswordButton;

    @FXML
    private Button personalInfoButton;

    @FXML
    private Button registerMemberButton;

    @FXML
    private Button backButton1;

    @FXML
    private Button backButton2;

    @FXML
    private AnchorPane topBarPane;

    @FXML
    private ImageView topRowView;

    @FXML
    private FlowPane mainFlowPane;

    @FXML
    void goBack1(ActionEvent event) {

    }

    @FXML
    void goBack2(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }

    @FXML
    void onModifyPassword(ActionEvent event) {

    }

    @FXML
    void toPersonalInfo(ActionEvent event) {

    }

    @FXML
    void toRegisterMember(ActionEvent event) {

    }
    
   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
