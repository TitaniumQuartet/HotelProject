package tiquartet.ClientModule.ui.hotelierui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import tiquartet.ClientModule.ui.customnode.StrategyPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.StrategyType;
import tiquartet.CommonModule.util.StringUtility;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.StrategyVO;

public class HotelStrategyController implements Initializable {

	@FXML
	private ChoiceBox<String> strategyTypeBox;

	@FXML
	private Button addStrategyButton;

	@FXML
	private Button goBackButton;

	@FXML
	private FlowPane flowPane;

	Parent previous;

	@FXML
	void buttonEntered(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.LIGHTSTEELBLUE, null, null)));
	}

	@FXML
	void buttonExited(MouseEvent event) {
		Button button = (Button) event.getTarget();
		button.setBackground(new Background(
				new BackgroundFill(Color.STEELBLUE, null, null)));
	}

	@FXML
	void onAddStrategy(ActionEvent event) {
		StrategyVO vo;
		if (LoginController.getCurrentUser().userType == UserType.酒店工作人员) {
			switch (strategyTypeBox.getSelectionModel().getSelectedIndex()) {
				case 0 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.BIRTHDAY;
					break;
				case 1 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.COMPANY;
					break;
				case 2 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.ROOMNUM;
					break;
				case 3 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.TIME;
					break;
				default :
					vo = null;
			}
		} else {
			switch (strategyTypeBox.getSelectionModel().getSelectedIndex()) {
				case 0 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.CIRCLE;
					break;
				case 1 :
					vo = new StrategyVO();
					vo.hotelID = LoginController.getCurrentUser().hotelID;
					vo.strategyType = StrategyType.TIME;
					break;
				default :
					vo = null;
			}
		}
		if (vo != null) {
			vo.discount = 1;
			try {
				HMSClient.getStrategyBL().addStrategy(vo);
			} catch (RemoteException e) {
				// 网络异常处理
				Alert alert = new Alert(AlertType.ERROR, "策略添加失败");
				alert.show();
				e.printStackTrace();
			}
			flowPane.getChildren().add(0, new StrategyPane(vo));
		}
	}

	@FXML
	void onGoBack(ActionEvent event) {
		if (LoginController.getCurrentUser().userType == UserType.酒店工作人员) {
			HMSClient.hotelierMainController.showSimply(previous);
		} else {
			HMSClient.marketerMainController.showHomePage();
		}
	}

	public void enter(Parent previous) {
		this.previous = previous;
		try {
			List<StrategyVO> list = HMSClient.getStrategyBL()
					.searchByHotel(LoginController.getCurrentUser().hotelID);
			flowPane.getChildren().clear();
			if (list != null) {
				for (StrategyVO vo : list) {
					flowPane.getChildren().add(new StrategyPane(vo));
				}
			}
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.getCurrentUser().userType == UserType.酒店工作人员) {
			strategyTypeBox.getItems().addAll(
					StringUtility.strategyName(StrategyType.BIRTHDAY),
					StringUtility.strategyName(StrategyType.COMPANY),
					StringUtility.strategyName(StrategyType.ROOMNUM),
					StringUtility.strategyName(StrategyType.TIME));
		} else {
			strategyTypeBox.getItems().addAll(
					StringUtility.strategyName(StrategyType.CIRCLE),
					StringUtility.strategyName(StrategyType.TIME));
		}
	}

}
