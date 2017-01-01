package tiquartet.ClientModule.ui.hotelierui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelDetailsVO;

public class HotelInfoController implements Initializable {

	@FXML
	private ChoiceBox<String> starBox;

	@FXML
	private TextField addressField;

	@FXML
	private TextArea introArea;

	@FXML
	private TextArea serviceArea;

	@FXML
	private Button modifyButton;

	@FXML
	private Button cancelButton;

	Parent previous;

	HotelDetailsVO hotelDetailsVO;

	@FXML
	void onCancel(ActionEvent event) {
		if (cancelButton.getText().equals("返回")) {
			HMSClient.hotelierMainController.showSimply(previous);
		} else {
			showInfo();
		}
	}

	@FXML
	void onModify(ActionEvent event) {
		if (modifyButton.getText().equals("修改")) {
			starBox.setDisable(false);
			addressField.setEditable(true);
			introArea.setEditable(true);
			serviceArea.setEditable(true);
			modifyButton.setText("确认");
			cancelButton.setText("取消");
		} else {
			hotelDetailsVO.star = starBox.getSelectionModel()
					.getSelectedIndex();
			hotelDetailsVO.address = addressField.getText();
			hotelDetailsVO.introduction = introArea.getText();
			hotelDetailsVO.serviceintro = serviceArea.getText();
			try {
				ResultMessage message = HMSClient.getHotelInfoBL()
						.modifyHotelInfo(hotelDetailsVO);
				if (!message.result) {
					Alert alert = new Alert(AlertType.ERROR, "修改失败");
					alert.show();
				}
				showInfo();
			} catch (RemoteException e) {
				Alert alert = new Alert(AlertType.ERROR, "网络异常");
				alert.show();
				e.printStackTrace();
			}
		}
	}

	/**
	 * 进入管理酒店信息界面时调用. 保存上一个页面并显示信息.
	 * 
	 * @param previous
	 */
	public void enter(Parent previous) {
		this.previous = previous;
		showInfo();
	}

	/**
	 * 显示最新的酒店信息，禁用编辑.
	 */
	void showInfo() {
		try {
			hotelDetailsVO = HMSClient.getHotelInfoBL().getHotelDetails(
					LoginController.getCurrentUser().hotelID,
					LoginController.getCurrentUser().userID);
			System.out.println(hotelDetailsVO.hotelID);
			starBox.getSelectionModel().select(hotelDetailsVO.star);
			starBox.setDisable(true);
			addressField.setText(hotelDetailsVO.address);
			addressField.setEditable(false);
			introArea.setText(hotelDetailsVO.introduction);
			introArea.setEditable(false);
			serviceArea.setText(hotelDetailsVO.serviceintro);
			serviceArea.setEditable(false);

			modifyButton.setVisible(true);
			modifyButton.setText("修改");
			cancelButton.setVisible(true);
			cancelButton.setText("返回");
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		starBox.getItems().addAll("无", "一星级", "二星级", "三星级", "四星级", "五星级");
	}

}
