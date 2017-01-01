package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.Encryptor;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserInfoUtility;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.UserVO;

public class AddHotelController implements Initializable {

    @FXML
    private ChoiceBox<String> cityBox;

    @FXML
    private ChoiceBox<String> districtBox;

    @FXML
    private TextField hotelNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView usernameTick;

    @FXML
    private ImageView passwordTick;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ImageView confirmPasswordTick;

    @FXML
    private Button addHotelButton;
    
    //当前选择的城市的编号
    private int cityID;

    @FXML
    void onAddHotel(ActionEvent event) {
    	try {
    		String districtName = districtBox.getItems().get(districtBox.getSelectionModel().getSelectedIndex());
        	int districtID = DistrictData.districtIDListOfCity(cityID).get(DistrictData.districtNameListOfCity(cityID).indexOf(districtName));
			ResultMessage message = HMSClient.getManageUserBL().addHotel(districtID, hotelNameField.getText());
			if(message.result){
				UserVO hotelier = new UserVO();
				hotelier.userName = usernameField.getText();
				hotelier.password = Encryptor.encriptMD5(passwordField.getText());
				hotelier.userType = UserType.酒店工作人员;
				hotelier.login = false;
				hotelier.hotelID = Integer.parseInt(message.message);
				hotelier.company = hotelNameField.getText();
				//需要数据层返回新增酒店的编号
				ResultMessage message2 = HMSClient.getManageUserBL().addUser(hotelier);
				if(message2.result){
					Alert success = new Alert(AlertType.INFORMATION, "酒店添加成功！");
					success.show();
					cityBox.getSelectionModel().clearSelection();
					districtBox.getItems().clear();
					hotelNameField.setText("");
					usernameField.setText("");
					passwordField.setText("");
					confirmPasswordField.setText("");
				}
			}
			Alert fail = new Alert(AlertType.ERROR, "酒店添加失败，"+message.failInfo);
			fail.show();
		} catch (RemoteException e) {
			//网络连接异常处理
			e.printStackTrace();
		}
    }
    
    /**
     * 检查各项输入是否正确完整.
     * 
     */
    private void checkInput(){
		try {
			//分别检查三个输入框内容是否合法.
			boolean usernameValid;
			usernameValid = UserInfoUtility.checkUserName(usernameField.getText())&&HMSClient.getUserMainBL().isUnregistered(usernameField.getText());
			usernameTick.setVisible(usernameValid);
	    	boolean passwordValid = UserInfoUtility.checkPassword(passwordField.getText());
	    	passwordTick.setVisible(passwordValid);
	    	boolean confirmValid = confirmPasswordField.getText().equals(passwordField.getText())&&passwordValid;
	    	confirmPasswordTick.setVisible(confirmValid);
	    	
	    	if(!districtBox.getSelectionModel().isEmpty()&&!hotelNameField.getText().isEmpty()&&usernameValid&&passwordValid&&confirmValid){
	    		addHotelButton.setDisable(false);
	    	}
	    	else {
				addHotelButton.setDisable(true);
			}
		} catch (RemoteException e) {
			//网络连接异常处理
			e.printStackTrace();
		}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		districtBox.setDisable(true);
		cityBox.getItems().addAll(DistrictData.getCityMap().values());
		cityBox.getSelectionModel().clearSelection();
		cityID = -1;
		
		addHotelButton.setDisable(true);
		
		//选择的城市发生变化时相应地改变商圈的选项
		cityBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				String city = cityBox.getItems().get((Integer) newValue);
				cityID = DistrictData.getCityIDOf(city);
				districtBox.getItems().clear();
				districtBox.getItems().addAll(DistrictData.districtNameListOfCity(cityID));
				districtBox.getSelectionModel().clearSelection();
				districtBox.setDisable(false);
			}
		});
		
		districtBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				checkInput();
			}
		});
		hotelNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
		usernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();
			}
		});
		passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();
			}
		});
		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
		
		hotelNameField.setTooltip(new Tooltip("请填写完整准确的酒店名称"));
		usernameField.setTooltip(new Tooltip("6-16个字符组成，只能包括数字字母下划线"));
		passwordField.setTooltip(new Tooltip("6-16个字符组成，不能出现空格"));
		confirmPasswordField.setTooltip(new Tooltip("再次输入密码以确认新密码"));
		addHotelButton.setTooltip(new Tooltip("请确认各项信息填写无误"));
	}

}
