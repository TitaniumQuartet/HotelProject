package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.Encryptor;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserInfoUtility;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.UserVO;

public class MarketerSectionController implements Initializable {

	@FXML
	private TableView<UserVO> marketerListTable;

	@FXML
	private TableColumn<UserVO, String> usernameColumn;

	@FXML
	private TableColumn<UserVO, String> realNameColumn;

	@FXML
	private Label usernameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField usernameField;

	@FXML
	private ImageView usernameTick;

	@FXML
	private ImageView passwordTick;

	@FXML
	private Label confirmPasswordLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private ImageView confirmPasswordTick;

	@FXML
	private Label realNameLabel;

	@FXML
	private TextField realNameField;

	@FXML
	private Button addMarketerButton;

	@FXML
	private Label modifyNameLabel;

	@FXML
	private TextField modifyNameField;

	@FXML
	private Label modifyPrompt;

	@FXML
	private Button saveModifyButton;

	private List<UserVO> userList;

	private UserVO currentSelected;

	/**
	 * 显示修改真实姓名的输入区域.
	 */
	private void showModifyName(String username, String realName) {
		modifyPrompt.setText("修改" + username + "用户的真实姓名");
		modifyNameLabel.setVisible(true);
		modifyNameField.setText(realName);
		modifyNameField.setVisible(true);
		saveModifyButton.setVisible(true);
	}

	/**
	 * 隐藏修改真实姓名的输入区域.
	 */
	private void hideModifyName() {
		modifyPrompt.setText("选择用户以修改真实姓名");
		modifyNameLabel.setVisible(false);
		modifyNameField.setVisible(false);
		saveModifyButton.setVisible(false);
	}

	/**
	 * 显示添加网站营销人员的输入组件.
	 * 
	 */
	private void showAddMarketer() {
		usernameLabel.setVisible(true);
		realNameLabel.setVisible(true);
		passwordLabel.setVisible(true);
		confirmPasswordLabel.setVisible(true);
		usernameField.setVisible(true);
		realNameField.setVisible(true);
		passwordField.setVisible(true);
		confirmPasswordField.setVisible(true);
		addMarketerButton.setText("确认");
		addMarketerButton.setDisable(true);
	}

	/**
	 * 隐藏增加营销人员的区域，保留一个按钮.
	 * 
	 */
	private void hideAddMarketer() {
		usernameLabel.setVisible(false);
		realNameLabel.setVisible(false);
		passwordLabel.setVisible(false);
		confirmPasswordLabel.setVisible(false);
		usernameField.setVisible(false);
		realNameField.setVisible(false);
		passwordField.setVisible(false);
		confirmPasswordField.setVisible(false);
		addMarketerButton.setText("增加人员");
	}

	/**
	 * 检查新增营销人员的输入内容.
	 * 
	 */
	private void checkInput() {
		try {
			// 分别检查三个输入框内容是否合法.
			boolean usernameValid;
			usernameValid = UserInfoUtility
					.checkUserName(usernameField.getText())
					&& HMSClient.getUserMainBL()
							.isUnregistered(usernameField.getText());
			usernameTick.setVisible(usernameValid);
			boolean passwordValid = UserInfoUtility
					.checkPassword(passwordField.getText());
			passwordTick.setVisible(passwordValid);
			boolean confirmValid = confirmPasswordField.getText()
					.equals(passwordField.getText()) && passwordValid;
			confirmPasswordTick.setVisible(confirmValid);

			if (usernameValid && passwordValid && confirmValid) {
				addMarketerButton.setDisable(false);
			} else {
				addMarketerButton.setDisable(true);
			}
		} catch (RemoteException e) {
			// 网络连接异常处理
			e.printStackTrace();
		}
	}

	@FXML
	void onAddMarketer(ActionEvent event) {
		// 保存相应的新增用户信息
		if (addMarketerButton.getText().equals("确认")) {
			try {
				UserVO marketer = new UserVO();
				marketer.userName = usernameField.getText();
				marketer.password = Encryptor
						.encriptMD5(passwordField.getText());
				marketer.userType = UserType.网站营销人员;
				marketer.login = false;
				marketer.hotelID = 0;
				ResultMessage message = HMSClient.getManageUserBL()
						.addUser(marketer);
				if (message.result) {
					hideAddMarketer();
					Alert success = new Alert(AlertType.INFORMATION,
							"营销人员添加成功！");
					success.show();
					userList.add(marketer);
					// 刷新表格内容
					realNameColumn.setVisible(false);
					usernameColumn.setVisible(false);
					realNameColumn.setVisible(true);
					usernameColumn.setVisible(true);
					marketerListTable.getSelectionModel().clearSelection();
				} else {
					Alert fail = new Alert(AlertType.ERROR,
							"营销人员添加失败，" + message.failInfo);
					fail.show();
				}
			} catch (RemoteException e) {
				// 网络连接异常处理
				e.printStackTrace();
			}

		}
		// 显示新增用户的输入框
		else {
			showAddMarketer();
		}
	}

	@FXML
	void onSaveModify(ActionEvent event) {
		try {
			String oldRealName = currentSelected.realName;
			currentSelected.realName = modifyNameField.getText();
			ResultMessage message = HMSClient.getManageUserBL()
					.update(currentSelected);
			if (message.result) {
				Alert success = new Alert(AlertType.INFORMATION, "信息修改成功！");
				success.show();
				// 刷新被修改的真实姓名
				realNameColumn.setVisible(false);
				realNameColumn.setVisible(true);
			} else {
				Alert fail = new Alert(AlertType.ERROR, "信息修改失败");
				fail.show();
				currentSelected.realName = oldRealName;
			}
		} catch (RemoteException e) {
			// 处理网络异常
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<UserVO> users = new ArrayList<>();
		// 需要调用RMI服务
		/*
		 * try { users = HMSClient.getManageUserBL().marketerList(); } catch
		 * (RemoteException e) { // 网络异常处理 e.printStackTrace(); }
		 */
		userList = new ArrayList<>();
		userList.addAll(users);
		marketerListTable.getItems().clear();
		marketerListTable.getItems().addAll(users);

		usernameColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserVO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							CellDataFeatures<UserVO, String> param) {
						return new SimpleStringProperty(
								param.getValue().userName);
					}

				});
		realNameColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserVO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							CellDataFeatures<UserVO, String> param) {
						return new SimpleStringProperty(
								param.getValue().realName);
					}

				});
		hideAddMarketer();
		hideModifyName();

		currentSelected = null;
		marketerListTable.getSelectionModel()
				.setSelectionMode(SelectionMode.SINGLE);
		marketerListTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<UserVO>() {

					@Override
					public void changed(
							ObservableValue<? extends UserVO> observable,
							UserVO oldValue, UserVO newValue) {
						currentSelected = newValue;
						if (currentSelected != null) {
							showModifyName(currentSelected.userName,
									currentSelected.realName);
						} else {
							hideModifyName();
						}
					}
				});

		usernameField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkInput();
					}
				});
		passwordField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkInput();
					}
				});
		confirmPasswordField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkInput();
					}
				});
	}

}
