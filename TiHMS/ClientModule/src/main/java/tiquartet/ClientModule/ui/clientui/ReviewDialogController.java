package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.StringUtility;
import tiquartet.CommonModule.vo.ReviewVO;

public class ReviewDialogController implements Initializable {

	@FXML
	private Slider rateSlider;

	@FXML
	private TextArea textarea;

	@FXML
	private Button confirmButton;

	@FXML
	private Button cancelButton;

	Stage reviewDialog;

	int hotelID;

	@FXML
	void cancelButton(ActionEvent event) {
		reviewDialog.close();
	}

	@FXML
	void onConfirm(ActionEvent event) {
		ReviewVO vo = new ReviewVO();
		vo.hotelID = hotelID;
		vo.review = textarea.getText();
		vo.score = (int) rateSlider.getValue();
		vo.time = StringUtility.dateToString(Calendar.getInstance().getTime());
		vo.userID = LoginController.getCurrentUser().userID;
		vo.userName = LoginController.getCurrentUser().userName;
		try {
			HMSClient.getHotelInfoBL().reviewHotel(vo);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
		reviewDialog.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rateSlider.setTooltip(new Tooltip("给酒店的体验打分，最高10分"));
	}

}
