package tiquartet.ClientModule.ui.usermainui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.Encryptor;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public class SignUpController implements Initializable {

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Button cancelSignUpButton;

    @FXML
    private Button finishSignUpButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label usernamePrompt;

    @FXML
    private Label passwordPrompt;

    @FXML
    private ImageView usernameSign;

    @FXML
    private ImageView newPasswordSign;

    @FXML
    private ImageView confirmPasswordSign;

    @FXML
    private TextField realNameField;

    /**
     * 取消注册，返回主登录界面.
     * @param event
     */
    @FXML
    void onCancelSignUpClicked(ActionEvent event) {
    	//切换至登陆界面
    	ResultMessage resultMessage = HMSClient.switchScene("/fxml/usermainui/login.fxml");
    	if(!resultMessage.result){
    		//界面加载失败的提示
    	}
    }

    /**
     * 点击注册按钮，完成注册.
     * @param event
     */
    @FXML
    void onFinishSignUpClicked(ActionEvent event) {
    	String realName = realNameField.getText().trim();
		if(realName.length()==0) realName = null;
		UserVO client = UserVO.getClientInstance(newUsernameField.getText(), Encryptor.encript(newPasswordField.getText()), realName);
		try {
			HMSClient.getManageUserBL().addUser(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * 检查注册页面各个输入框的文本，显示相应的提示.
	 * 
	 */
	private void checkSignUp(){
		
		int l = newUsernameField.getText().length();
		boolean usernameValid = false, passwordValid = false, confirmpwValid = false;
		
		//检查用户名输入
		if(newUsernameField.getText().matches(".[^a-zA-Z0-9_].")){
			//若输入的用户名中包含非法字符
			usernamePrompt.setText("* 仅包含字母数字下划线");
		}
		else if(l<6||l>16){
			//若输入的用户名长度不正确
			usernamePrompt.setText("* 6-16个字符");
		}
		else{
			try {
				if(!HMSClient.getUserMainBL().isUnregistered(newUsernameField.getText())){
					//若该用户名已被注册
					usernamePrompt.setText("* 已经被注册");
				}
				else{
					//若用户名合法
					usernamePrompt.setVisible(false);
					usernameSign.setVisible(true);
					usernameValid = true;
				}
			} catch (RemoteException e) {
				usernamePrompt.setText("网络连接异常");
				e.printStackTrace();
			}
		}
		if(!usernameValid){
			usernameSign.setVisible(false);
			usernamePrompt.setVisible(true);
			finishSignUpButton.setDisable(true);
		}
		
		//检查密码输入
		l = newPasswordField.getText().length();
		if(newPasswordField.getText().contains(" ")){
			//若输入的密码包含空格
			passwordPrompt.setText("* 不能包括空格");
			passwordPrompt.setVisible(true);
			newPasswordSign.setVisible(false);
			finishSignUpButton.setDisable(true);
		}
		else if(l<6||l>16){
			//若输入的密码长度不正确
			passwordPrompt.setText("* 6-16个字符");
			passwordPrompt.setVisible(true);
			newPasswordSign.setVisible(false);
			finishSignUpButton.setDisable(true);
		}
		else{
			//若输入的密码长度正确
			passwordValid=true;
			passwordPrompt.setVisible(false);
			newPasswordSign.setVisible(true);
		}
		
		//检查密码确认输入
		if(!confirmPasswordField.getText().equals(newPasswordField.getText())){
			//若确认密码内容与密码不一致
			confirmPasswordSign.setVisible(true);
			//显示红叉
		}
		else {
			//若确认密码一致
			confirmpwValid=true;
			confirmPasswordSign.setVisible(false);
		}
		
		//三个输入均正确时按钮可用
		if(usernameValid&&passwordValid&&confirmpwValid) finishSignUpButton.setDisable(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newUsernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSignUp();
			}
		});
		newPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSignUp();			
			}
		});
		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSignUp();			
			}
		});
		realNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSignUp();			
			}
		});
	}
    
    

}
