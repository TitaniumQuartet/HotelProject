package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.MemberVO;

public class MemberSignUpController implements Initializable {

	@FXML
	private ChoiceBox<String> memberTypeBox;

	@FXML
	private Label infoLabel;

	@FXML
	private DatePicker birthdayPicker;

	@FXML
	private Button signUpButton;

	@FXML
	private TextField companyField;

	@FXML
	private Button cancelButton;

	Parent previous;

	@FXML
	void onCancelClicked(ActionEvent event) {
		HMSClient.clientMainController.showSimply(previous);
		HMSClient.clientMainController.renewMemberButton();
	}

	@FXML
	void onSignUpClicked(ActionEvent event) {
		MemberVO memberVO = new MemberVO();
		if (memberTypeBox.getSelectionModel().getSelectedIndex() == 0) {
			// 个人会员
			memberVO.birthday = birthdayPicker.getValue().toString();
			memberVO.memberType = MemberType.个人会员;
		} else {
			// 企业会员
			memberVO.companyName = companyField.getText();
			memberVO.memberType = MemberType.企业会员;
		}
		memberVO.memberRank = 1;
		memberVO.userID = LoginController.getCurrentUser().userID;
		try {
			ResultMessage message = HMSClient.getManageUserBL()
					.memberSignIn(memberVO);
			if (message.result) {
				Alert cancel = new Alert(AlertType.INFORMATION,
						LoginController.getCurrentUser().userName + "注册"
								+ memberVO.memberType.name() + "成功！");
				cancel.show();
				HMSClient.clientMainController.showSimply(previous);
				HMSClient.clientMainController.renewMemberButton();
			} else {
				Alert cancel = new Alert(AlertType.ERROR, "订单提交失败");
				cancel.show();
			}
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	public void enter(Parent previous) {
		this.previous = previous;
		memberTypeBox.getSelectionModel().clearSelection();
		infoLabel.setVisible(false);
		birthdayPicker.setVisible(false);
		companyField.setVisible(false);
		signUpButton.setVisible(false);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberTypeBox.getItems().addAll("个人会员", "企业会员");
		memberTypeBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						int index = (Integer) newValue;
						if (index > -1) {
							signUpButton.setDisable(true);
							signUpButton.setVisible(true);
						}
						if (index == 0) {
							// 选择个人会员
							infoLabel.setText("生日");
							birthdayPicker.setVisible(true);
							birthdayPicker.setValue(null);
							companyField.setVisible(false);
						} else if (index == 1) {
							// 选择企业会员
							infoLabel.setText("企业名称");
							birthdayPicker.setVisible(false);
							companyField.setText("");
							companyField.setVisible(true);
						}
					}
				});
		birthdayPicker.valueProperty()
				.addListener(new ChangeListener<LocalDate>() {
					@Override
					public void changed(
							ObservableValue<? extends LocalDate> observable,
							LocalDate oldValue, LocalDate newValue) {
						if (newValue != null)
							signUpButton.setVisible(false);
					}
				});
		companyField.accessibleTextProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						if (!newValue.isEmpty())
							signUpButton.setDisable(false);
					}
				});
	}

}
