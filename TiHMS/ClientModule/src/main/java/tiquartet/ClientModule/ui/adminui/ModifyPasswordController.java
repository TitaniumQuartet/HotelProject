package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.Encryptor;

public class ModifyPasswordController implements Initializable{

    @FXML
    private Button confirmModifyButton;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label newPasswordPrompt;
    
    @FXML
    private ImageView newPasswordSign;
    
    @FXML
    private ImageView newPasswordTick;

    @FXML
    private ImageView confirmPasswordSign;
    
    @FXML
    private ImageView confirmPasswordTick;
    
    @FXML
    private ImageView currentPasswordSign;
    
    @FXML
    private ImageView currentPasswordTick;
    

    @FXML
    void onConfirmModify(ActionEvent event) {
    	//未实现方法
    }
    
    /**
     * 用于在任意输入框失去焦点后更新按钮状态并提供提示.
     * 
     */
    private void checkPassword(){
    	
    	String code;
    	boolean currentLegit = false, newCodeLegit = false, confirmLegit = false;
    	
    	//检查当前密码输入是否正确
    	code = currentPasswordField.getText();
    	if(code.isEmpty()){
    		currentPasswordSign.setVisible(false);
    		currentPasswordTick.setVisible(false);
    	}
    	else if(Encryptor.encript(code).equals(LoginController.getCurrentUser().password)){
    		currentPasswordSign.setVisible(false);
    		currentPasswordTick.setVisible(true);
    		currentLegit = true;
    	}
    	else{
    		currentPasswordSign.setVisible(true);
    		currentPasswordTick.setVisible(false);
    	}
    	
    	//检查新密码输入是否合法
    	code = newPasswordField.getText();
    	if(code.isEmpty()){
    		newPasswordSign.setVisible(false);
    		newPasswordTick.setVisible(false);
    	}
    	else if(code.length()>5&&code.length()<17&&!code.contains(" ")){
    		newPasswordSign.setVisible(false);
    		newPasswordTick.setVisible(true);
    		newCodeLegit = true;
    	}
    	else{
    		newPasswordSign.setVisible(true);
    		newPasswordTick.setVisible(false);
    	}
    	
    	//检查重新输入的 密码是否合法
    	code = confirmPasswordField.getText();
    	if(code.isEmpty()){
    		confirmPasswordSign.setVisible(false);
    		confirmPasswordTick.setVisible(false);
    	}
    	else if(newCodeLegit&&code.equals(newPasswordField.getText())){
    		confirmPasswordSign.setVisible(false);
    		confirmPasswordTick.setVisible(true);
    		confirmLegit = true;
    	}
    	else{
    		confirmPasswordSign.setVisible(true);
    		confirmPasswordTick.setVisible(false);
    	}
    	
    	//有一项输入不合法就禁用完成注册按钮
    	if(currentLegit&&newCodeLegit&&confirmLegit) confirmModifyButton.setDisable(false);
    	else confirmModifyButton.setDisable(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
		newPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
	}

}
