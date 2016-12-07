package tiquartet.ClientModule.ui.adminui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.ResultMessage;

/**
 * 酒店管理系统管理员的主页面（左边栏及底部）控制器类
 * @author greatlyr
 *
 */
public class AdminMainController implements Initializable{
	
	public Parent searchUserSection;
	
	public Parent modifyPassword;
	
	public Parent addHotelSection;

    @FXML
    private Hyperlink logoutLink;

    @FXML
    private Hyperlink modifyPasswordLink;

    @FXML
    private ImageView topBarImageView;

    @FXML
    private Button hotelierSectionButton;

    @FXML
    private Button searchSectionButton;

    @FXML
    private Button marketerSectionButton;

    @FXML
    private Button addHotelSectionButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void onLogoutClicked(ActionEvent event) {
    	try {
			HMSClient.getUserMainBL().logout(LoginController.getCurrentUser().userID);
			HMSClient.switchScene("/fxml/usermainui/login.fxml");
		} catch (RemoteException e) {
			Alert alert = new Alert(AlertType.ERROR, "连接服务器失败");
			alert.showAndWait();
			e.printStackTrace();
		}
    }

    @FXML
    void onModifyPassword(ActionEvent event) {
    	showSection(modifyPassword);
    }

    @FXML
    void toAddHotelSection(ActionEvent event) {
    	showSection(addHotelSection);
    }

    @FXML
    void toHotelierSection(ActionEvent event) {

    }

    @FXML
    void toMarketerSection(ActionEvent event) {

    }

    @FXML
    void toSearchSection(ActionEvent event) {
    	showSection(searchUserSection);
    }
    
    /**
     * 切换界面右侧主要内容.
     * @param section 要显示的组件
     * @return
     */
    public ResultMessage showSection(Parent section) {
    	mainPane.getChildren().clear();
		mainPane.getChildren().add(section);
		return new ResultMessage(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	try {
    		FXMLLoader fxmlLoader;
    		//加载搜索用户组件
    		fxmlLoader = new FXMLLoader();
        	fxmlLoader.setLocation(getClass().getResource("/fxml/adminui/searchSection.fxml"));
			searchUserSection = fxmlLoader.load();
			//加载修改密码组件
			fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/adminui/modifyPassword.fxml"));
			modifyPassword = fxmlLoader.load();
			//加载增加酒店组件
			fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/adminui/addHotel.fxml"));
			addHotelSection = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
