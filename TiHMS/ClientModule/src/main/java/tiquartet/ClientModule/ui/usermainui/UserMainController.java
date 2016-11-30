package tiquartet.ClientModule.ui.usermainui;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.vo.UserVO;

/**
 * 主登录界面和客户注册界面的控制器类.
 * 
 * @author greatlyr
 *
 */
public class UserMainController {

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
	private Button signInButton;

	@FXML
	private Label loginWarningLabel;

	@FXML
	private TextField newUsernameField;

	@FXML
	private PasswordField newPasswordField;

	@FXML
	private Button cancelSignInButton;

	@FXML
	private Button finishSignInButton;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private Label UsernamePrompt;

	@FXML
	private Label PasswordPrompt;

	@FXML
	private ImageView UsernameSign;

	@FXML
	private ImageView NewPasswordSign;

	@FXML
	private ImageView ConfirmPasswordSign;

	@FXML
	private TextField RealNameField;

	/**
	 * 点击主登录界面的登录按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onLoginCLicked(ActionEvent event) {
		boolean usernamevalid, passwordvalid;
		String username = usernameField.getText(),
				password = passwordField.getText();
		usernamevalid = username.length() > 5 && username.length() < 17
				&& !username.matches(".[^a-zA-Z0-9_].");
		/*
		 * 能匹配该正则表达式表示用户名包含任意不属于英文字母、数字或下划线的字符
		 * 用户名和密码均为6-16个字符长的字符串
		 */
		passwordvalid = password.length() > 5 && password.length() < 17;
		if(!usernamevalid||!passwordvalid){
			if(!usernamevalid&&!passwordvalid) loginWarningLabel.setText("用户名和密码长度错误");
			else if(!usernamevalid) loginWarningLabel.setText("用户名长度错误");
			else loginWarningLabel.setText("密码长度错误");
			loginWarningLabel.setVisible(true);
			return;
		}
		//对用户名密码的输入内容有效性的即时判断可以用ChangeListener实现
		
		try {
			UserVO user = HMSClient.getUserMainBL().login(username, password);
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
	void onSignInClicked(ActionEvent event) {
		
	}

	/**
	 * 点击客户注册界面的取消按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onCancelSignInClicked(ActionEvent event) {

	}

	/**
	 * 点击客户注册界面的注册按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onFinishSignInClicked(ActionEvent event) {

	}

	/**
	 * 取得当前客户端登录的用户的信息，未登录状态下值为null.
	 * 
	 * @return
	 */
	public static UserVO getCurrentUser() {
		return UserMainController.currentUser;
	}

}
