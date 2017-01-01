package tiquartet.ClientModule.ui.usermainui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserInfoUtility;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.UserVO;

/**
 * 主登录界面和客户注册界面的控制器类.
 * 
 * @author greatlyr
 *
 */
public class LoginController implements Initializable {

	public static UserVO currentUser = null;

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
	private AnchorPane anchorPane;

	/**
	 * 点击主登录界面的登录按钮触发.
	 * 
	 * @param event
	 */
	@FXML
	void onLoginCLicked(ActionEvent event) {

		try {
			ResultMessage resultMessage = HMSClient.getUserMainBL().login(
					usernameField.getText(),
					Encryptor.encriptMD5(passwordField.getText()));
			if (!resultMessage.result) {
				// 登录失败
				loginWarningLabel.setText("用户名或密码输入错误");
				loginWarningLabel.setVisible(true);
			}
			// 登录成功
			if (rememberBox.isSelected())
				UserPreferences.setLoginPref(usernameField.getText(),
						passwordField.getText());
			else
				UserPreferences.setLoginPref("", "");
			currentUser = HMSClient.getManageUserBL()
					.accurateSearch(usernameField.getText());
			/*
			 * 界面切换的实现
			 */
			if (currentUser.userType == UserType.网站管理员) {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/fxml/adminui/adminMain.fxml"));
				HMSClient.showScene(new Scene(loader.load(), 1280, 800));
				HMSClient.adminMainController = loader.getController();
			} else if (currentUser.userType == UserType.客户) {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/clientui/clientMain.fxml"));
				HMSClient.showScene(new Scene(loader.load(), 1280, 800));
				HMSClient.clientMainController = loader.getController();
			} else if (currentUser.userType == UserType.酒店工作人员) {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/hotelierui/hotelierMain.fxml"));
				HMSClient.showScene(new Scene(loader.load(), 1280, 800));
				HMSClient.hotelierMainController = loader.getController();
			} else if (currentUser.userType == UserType.网站营销人员) {
				FXMLLoader loader = new FXMLLoader(getClass()
						.getResource("/fxml/marketerui/marketerMain.fxml"));
				HMSClient.showScene(new Scene(loader.load(), 1280, 800));
				HMSClient.marketerMainController = loader.getController();
			}
		} catch (RemoteException | NullPointerException e) {
			// 网络异常处理
			loginWarningLabel.setText("网络连接异常");
			loginWarningLabel.setVisible(true);
			e.printStackTrace();
		} catch (IOException e) {
			// FXML加载出错
			Alert fail = new Alert(AlertType.ERROR, "界面加载失败");
			fail.show();
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
		// 切换至注册界面
		ResultMessage resultMessage = HMSClient
				.switchScene("/fxml/usermainui/signUp.fxml");
		if (!resultMessage.result) {
			// 界面加载失败的提示
		}
	}

	@FXML
	void paneClicked(MouseEvent event) {
		anchorPane.requestFocus();
	}

	/**
	 * 检查登录页面各个输入框的文本，显示相应的提示.
	 * 
	 */
	private void checkLogin() {
		boolean usernamevalid, passwordvalid;
		String username = usernameField.getText(),
				password = passwordField.getText();

		// 检查用户名输入
		usernamevalid = username.isEmpty()
				|| UserInfoUtility.checkUserName(username);
		/*
		 * 能匹配该正则表达式表示用户名包含任意不属于英文字母、数字或下划线的字符 用户名和密码均为6-16个字符长的字符串
		 */

		// 检查密码输入
		passwordvalid = password.isEmpty()
				|| UserInfoUtility.checkPassword(password);

		if (!usernamevalid || !passwordvalid) {
			if (!usernamevalid && !passwordvalid)
				loginWarningLabel.setText("用户名和密码长度错误");
			else if (!usernamevalid)
				loginWarningLabel.setText("用户名长度错误");
			else
				loginWarningLabel.setText("密码长度错误");
			loginWarningLabel.setVisible(true);
			loginButton.setDisable(true);
		} else {
			loginWarningLabel.setVisible(false);
			loginButton.setDisable(false);
		}
	}

	public void getReadyToShow() {
		usernameField.setText("");
		passwordField.setText("");
		UserVO local = UserPreferences.getLoginPref();
		if (local != null) {
			usernameField.setText(local.userName);
			passwordField.setText(local.password);
			rememberBox.setSelected(true);
		} else
			rememberBox.setSelected(false);
	}

	/**
	 * 取得当前客户端登录的用户的信息，未登录状态下值为null.
	 * 
	 * @return
	 */
	public static UserVO getCurrentUser() {
		return LoginController.currentUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 给所有输入框添加焦点状态变化的监听器
		usernameField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkLogin();
					}
				});
		passwordField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkLogin();
					}
				});
		currentUser = null;

		UserVO local = UserPreferences.getLoginPref();
		if (local != null) {
			usernameField.setText(local.userName);
			passwordField.setText(local.password);
		}

	}

}
