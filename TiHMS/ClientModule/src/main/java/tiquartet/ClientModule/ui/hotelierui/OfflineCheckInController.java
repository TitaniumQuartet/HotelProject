package tiquartet.ClientModule.ui.hotelierui;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import tiquartet.ClientModule.ui.customnode.RoomTypePane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.UserVO;

public class OfflineCheckInController implements Initializable {

	@FXML
	private HBox roomTypeHBox;

	@FXML
	private FlowPane roomTypeFlowPane;

	@FXML
	private DatePicker inDateBox;

	@FXML
	private DatePicker outDateBox;

	@FXML
	private Label roomNumLabel;

	@FXML
	private ChoiceBox<Integer> roomNumBox;

	@FXML
	private Label guestNameLabel;

	@FXML
	private TextField guestNameField;

	@FXML
	private Button confirmButton;

	@FXML
	private FlowPane confirmPane;

	ToggleGroup group;

	PreOrderVO offline;
	UserVO user;
	List<RoomTypeVO> types;

	@FXML
	void onConfirm(ActionEvent event) {
		offline.numOfRoom = roomNumBox.getSelectionModel().getSelectedItem();
		offline.clientRealName = guestNameField.getText();
		try {
			List<String> message = HMSClient.getCreateOrderBL()
					.offLine(offline);
			if (!message.isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION,
						"线下入住已确认，入住客房：" + String.join("，", message));
				alert.show();
				HMSClient.hotelierMainController.showHomePage();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "无法分配房间，请重新尝试");
				alert.show();
			}
		} catch (RemoteException e) {
			Alert alert = new Alert(AlertType.ERROR, "网络异常");
			alert.show();
			e.printStackTrace();
		}
	}

	@FXML
	void paneClicked(MouseEvent event) {
		Parent parent = (Parent) event.getTarget();
		parent.requestFocus();
	}

	public void enter(LocalDate outDate) {
		inDateBox.setValue(LocalDate.now());
		outDateBox.setValue(outDate);
		confirmPane.setVisible(false);

		offline = new PreOrderVO();
		offline.hotelID = user.hotelID;
		offline.hotelName = HMSClient.hotelierMainController.hotelInfo.hotelName;
		offline.leaveTime = outDate.toString();
		offline.numOfRoom = 1;
		offline.startTime = LocalDate.now().toString();
		try {
			types = HMSClient.getHotelInfoBL().availableRoomType(offline);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	void renewRoomTypes() {
		roomTypeHBox.getChildren().clear();
		group.getToggles().clear();
		for (RoomTypeVO vo : types) {
			RoomTypePane pane = new RoomTypePane(vo);
			roomTypeHBox.getChildren().add(pane);
			group.getToggles().add(pane.button);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = LoginController.getCurrentUser();
		group.selectedToggleProperty()
				.addListener(new ChangeListener<Toggle>() {
					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						if (oldValue == null) {
							confirmPane.setVisible(true);
							int num = types.get(group.getToggles()
									.indexOf(newValue)).number;
							roomNumBox.getItems().clear();
							for (int i = 1; i <= num; i++)
								roomNumBox.getItems().add(i);
							roomNumBox.getSelectionModel().select(0);
						} else if (newValue == null) {
							confirmPane.setVisible(false);
						}
					}
				});
		inDateBox.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				offline.startTime = newValue.toString();
				renewRoomTypes();
			}
		});
		outDateBox.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				offline.leaveTime = newValue.toString();
				renewRoomTypes();
			}
		});
	}

}
