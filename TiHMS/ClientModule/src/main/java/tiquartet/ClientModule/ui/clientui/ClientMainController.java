package tiquartet.ClientModule.ui.clientui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.MemberType;

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
    
    private Parent modifyPasswordPane = null;
    
    private Parent HomePage = null;

    @FXML
    void goBack1(ActionEvent event) {

    }

    @FXML
    void goBack2(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {
    	try {
			HMSClient.getUserMainBL().logout(LoginController.getCurrentUser().userID);
			HMSClient.showScene(HMSClient.loginScene);
		} catch (RemoteException e) {
			//网络连接错误
			e.printStackTrace();
		}
    }

    @FXML
    void onModifyPassword(ActionEvent event) {
    	if(modifyPasswordPane == null){
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminui/modifyPassword.fxml"));
				modifyPasswordPane = loader.load();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
    	}
    	mainFlowPane.getChildren().clear();
    	mainFlowPane.getChildren().add(modifyPasswordPane);
    }

    @FXML
    void toPersonalInfo(ActionEvent event) {

    }

    @FXML
    void toRegisterMember(ActionEvent event) {

    }
    
    void showHomePage(){
    	if(HomePage == null){
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientui/homePage.fxml"));
				HomePage = loader.load();
				HomePageController homePageController = loader.getController();
				//homePageController.setContent();
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
    	}
    	mainFlowPane.getChildren().clear();
    	mainFlowPane.getChildren().add(HomePage);
    }
    
    public void renewMemberButton(){
    	registerMemberButton.setVisible(LoginController.getCurrentUser().memberType==MemberType.非会员);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//renewMemberButton();
		showHomePage();
	}

}
