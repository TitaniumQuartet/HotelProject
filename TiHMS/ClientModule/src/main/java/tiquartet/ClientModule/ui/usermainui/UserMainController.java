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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

/**
 * 主登录界面和客户注册界面的控制器类.
 * 
 * @author greatlyr
 *
 */
public class UserMainController implements Initializable {

	private static UserVO currentUser = null;
	
	@FXML
	private CheckBox rememberBox;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button signUpButton;

	@FXML
	private Label loginWarningLabel;

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
	 * 点击主登录界面的登录按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onLoginCLicked(ActionEvent event) {
		
		try {
			UserVO user = HMSClient.getUserMainBL().login(usernameField.getText(), passwordField.getText());
			if(user==null){
				//登录失败
				loginWarningLabel.setText("用户名或密码输入错误");
				loginWarningLabel.setVisible(true);
			}
			//登录成功
			currentUser = user;
			/*
			 * 界面切换的实现
			 */
		} catch (RemoteException | NullPointerException e) {
			//调用失败
			loginWarningLabel.setText("网络连接异常");
			loginWarningLabel.setVisible(true);
			e.printStackTrace();
		}
	}

	/**
	 * 点击主登录界面的注册按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onSignUpClicked(ActionEvent event) {
		//切换至注册界面
		ResultMessage result = HMSClient.switchScene("/fxml/usermainui/signin.fxml");
		
		//待添加界面加载失败的代码
	}

	/**
	 * 点击客户注册界面的取消按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onCancelSignUpClicked(ActionEvent event) {
		//切换至登陆界面
		ResultMessage result = HMSClient.switchScene("/fxml/usermainui/login.fxml");
		
		//待添加界面加载失败的代码
	}

	/**
	 * 点击客户注册界面的注册按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onFinishSignUpClicked(ActionEvent event) {
		String realName = realNameField.getText().trim();
		if(realName.length()==0) realName = null;
		UserVO client = UserVO.getClientInstance(usernameField.getText(), passwordField.getText(), realName);
		try {
			HMSClient.getManageUserBL().addUser(client);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查登录页面各个输入框的文本，显示相应的提示.
	 * 
	 */
	void checkLogin(){
		boolean usernamevalid, passwordvalid;
		String username = usernameField.getText(),
				password = passwordField.getText();
		
		//检查用户名输入
		usernamevalid = username.length() > 5 && username.length() < 17
				&& !username.matches(".[^a-zA-Z0-9_].");
		/*
		 * 能匹配该正则表达式表示用户名包含任意不属于英文字母、数字或下划线的字符
		 * 用户名和密码均为6-16个字符长的字符串
		 */
		
		//检查密码输入
		passwordvalid = password.length() > 5 && password.length() < 17;
		
		if(!usernamevalid||!passwordvalid){
			if(!usernamevalid&&!passwordvalid) loginWarningLabel.setText("用户名和密码长度错误");
			else if(!usernamevalid) loginWarningLabel.setText("用户名长度错误");
			else loginWarningLabel.setText("密码长度错误");
			loginWarningLabel.setVisible(true);
			loginButton.setDisable(true);
		}
		else{
			loginWarningLabel.setVisible(false);
			loginButton.setDisable(false);
		}
	}
	
	/**
	 * 检查注册页面各个输入框的文本，显示相应的提示.
	 * 
	 */
	void checkSignUp(){
		
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
		if(l<6||l>16){
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
		if(usernameValid&&passwordValid&&confirmpwValid) signUpButton.setDisable(false);
	}

	/**
	 * 取得当前客户端登录的用户的信息，未登录状态下值为null.
	 * 
	 * @return
	 */
	public static UserVO getCurrentUser() {
		return UserMainController.currentUser;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//给所有输入框添加焦点状态变化的监听器
		usernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkLogin();				
			}
		});
		passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkLogin();
			}
		});
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
