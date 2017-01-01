package tiquartet.ClientModule.ui.clientui;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public class ClientInfoController {

	@FXML
	private Label infoLabel;

	@FXML
	private Button editButton;

	@FXML
	private TextField realNameField;

	@FXML
	private Button cancelButton;

	@FXML
	private Label usernameLabel;

	@FXML
	private TextField contactField;

	@FXML
	private Label creditLabel;

	@FXML
	private Label memberLevelValue;

	@FXML
	private Label memberTypeLabel;

	@FXML
	private Label memberLevelLabel;

	@FXML
	private Label memberInfoLabel;

	@FXML
	private Label memberInfoValue;

	Parent previous;

	public void enter(Parent previous) {
		this.previous = previous;
		UserVO userVO = LoginController.getCurrentUser();
		usernameLabel.setText(userVO.userName);
		realNameField.setText(userVO.realName);
		realNameField.setEditable(false);
		contactField.setText(userVO.phone);
		contactField.setEditable(false);
		creditLabel.setText(String.format("%.2f", userVO.credit));
		memberTypeLabel.setText(userVO.memberType.name());
		memberLevelLabel.setText("VIP" + userVO.memberLevel);
		memberLevelLabel.setVisible(userVO.memberType != MemberType.非会员);
		memberLevelValue.setVisible(userVO.memberType != MemberType.非会员);
		memberInfoLabel.setVisible(userVO.memberType != MemberType.非会员);
		memberInfoValue.setVisible(userVO.memberType != MemberType.非会员);
		if (userVO.memberType == MemberType.个人会员) {
			memberInfoLabel.setText("生日");
			memberInfoValue.setText(userVO.birthday);
		} else if (userVO.memberType == MemberType.企业会员) {
			memberInfoLabel.setText("企业名称");
			memberInfoValue.setText(userVO.company);
		}
		editButton.setText("编辑");
		cancelButton.setText("返回");
	}

	@FXML
	void onCancelClicked(ActionEvent event) {
		if (editButton.getText().equals("编辑")) {
			// 离开信息界面
			HMSClient.clientMainController.showSimply(previous);
		} else {
			// 取消编辑
			enter(previous);
		}
	}

	@FXML
	void onEditClicked(ActionEvent event) {
		if (editButton.getText().equals("编辑")) {
			// 开始编辑信息
			editButton.setText("保存");
			cancelButton.setText("取消");
			realNameField.setEditable(true);
			contactField.setEditable(true);
		} else {
			// 保存信息
			UserVO userVO = LoginController.getCurrentUser();
			String oldRealName = userVO.realName;
			String oldContact = userVO.phone;
			userVO.realName = realNameField.getText();
			userVO.phone = contactField.getText();
			try {
				ResultMessage message = HMSClient.getManageUserBL()
						.update(userVO);
				if (!message.result) {
					userVO.realName = oldRealName;
					userVO.phone = oldContact;
					Alert cancel = new Alert(AlertType.ERROR, "信息保存失败");
					cancel.show();
					enter(previous);
				} else {
					Alert cancel = new Alert(AlertType.INFORMATION,
							"个人信息更新成功！");
					cancel.show();
					enter(previous);
				}
			} catch (RemoteException e) {
				userVO.realName = oldRealName;
				userVO.phone = oldContact;
				Alert cancel = new Alert(AlertType.ERROR, "信息保存失败");
				cancel.show();
				enter(previous);
				e.printStackTrace();
			}
		}
	}

}
