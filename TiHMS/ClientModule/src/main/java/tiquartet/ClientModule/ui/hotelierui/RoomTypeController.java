package tiquartet.ClientModule.ui.hotelierui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;

public class RoomTypeController implements Initializable {

	@FXML
	private ChoiceBox<String> typeBox;

	@FXML
	private TextField nameField;

	@FXML
	private TextField priceField;

	@FXML
	private TextArea introArea;

	@FXML
	private Label numLabel;

	@FXML
	private Button deleteButton;

	@FXML
	private Button modifyButton;

	@FXML
	private Button addTypeButton;

	@FXML
	private TableView<RoomVO> roomTable;

	@FXML
	private TableColumn<RoomVO, String> numColumn;

	@FXML
	private TableColumn<RoomVO, String> statusColumn;

	@FXML
	private Button deleteRoomButton;

	@FXML
	private TextField newRoomBox;

	@FXML
	private Button newRoomButton;

	List<RoomTypeVO> typeList;
	List<RoomVO> roomList;
	Parent previous;

	@FXML
	void onAddType(ActionEvent event) {
		RoomTypeVO vo = new RoomTypeVO();
		vo.hotelID = LoginController.getCurrentUser().hotelID;
		vo.number = 0;
		vo.price = 0;
		vo.roomType = "未命名";
		vo.typeIntroduction = "尚未填写";
		try {
			ResultMessage message = HMSClient.getManageRoomBL().addRoomType(vo);
			if (message.result) {
				typeList.add(0, vo);
				setTypes();
			} else {
				Alert error = new Alert(AlertType.ERROR, "新增类型失败");
				error.show();
			}
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@FXML
	void onDeleteRoom(ActionEvent event) {
		try {
			HMSClient.getManageRoomBL().deleteRoom(
					roomTable.getSelectionModel().getSelectedItem().roomID);
			// 更新列表内容
			roomList.remove(roomTable.getSelectionModel().getSelectedItem());
			roomTable.getItems().clear();
			roomTable.getItems().addAll(roomList);
			roomTable.setVisible(false);
			roomTable.setVisible(true);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@FXML
	void onNewRoom(ActionEvent event) {
		if (!newRoomBox.getText().isEmpty()) {
			RoomVO vo = new RoomVO();
			vo.hotelId = LoginController.getCurrentUser().hotelID;
			vo.roomNum = newRoomBox.getText();
			vo.roomStatus = RoomStatus.空闲;
			vo.roomType = typeList.get(
					typeBox.getSelectionModel().getSelectedIndex()).roomTypeId;
			try {
				ResultMessage message = HMSClient.getManageRoomBL().addRoom(vo);
				if (message.result) {
					roomList.add(vo);
					roomTable.getItems().clear();
					roomTable.getItems().addAll(roomList);
					roomTable.setVisible(false);
					roomTable.setVisible(true);
				} else {
					Alert error = new Alert(AlertType.ERROR, "增加客房失败");
					error.show();
				}
			} catch (RemoteException e) {
				// 网络异常处理
				e.printStackTrace();
			}

		}
	}

	@FXML
	void onModifyType(ActionEvent event) {
		if (modifyButton.getText().equals("修改")) {
			nameField.setEditable(true);
			priceField.setEditable(true);
			introArea.setEditable(true);
			modifyButton.setText("保存");
			deleteButton.setText("取消");
		} else {
			RoomTypeVO vo = typeList
					.get(typeBox.getSelectionModel().getSelectedIndex());
			vo.roomType = nameField.getText();
			vo.price = Double.parseDouble(priceField.getText());
			vo.typeIntroduction = introArea.getText();
			try {
				ResultMessage message = HMSClient.getManageRoomBL()
						.modifyRoomType(vo);
				if (message.result) {
					nameField.setEditable(false);
					priceField.setEditable(false);
					introArea.setEditable(false);
				}
			} catch (RemoteException e) {
				// 网络异常处理
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onDeleteType(ActionEvent event) {
		if (deleteButton.getText().equals("删除类型")) {
			try {
				Alert confirm = new Alert(AlertType.CONFIRMATION,
						"将删除此类型的所有客房");
				confirm.show();
				if (confirm.getResult().equals(ButtonType.OK)) {
					ResultMessage message = HMSClient.getManageRoomBL()
							.deleteRoomType(
									LoginController.getCurrentUser().hotelID,
									typeList.get(typeBox.getSelectionModel()
											.getSelectedIndex()).roomTypeId);
					if (message.result)
						for (RoomVO room : roomList) {
							HMSClient.getManageRoomBL().deleteRoom(room.roomID);
						}
					else {
						// 删除类型失败
						Alert error = new Alert(AlertType.ERROR, "删除失败");
						error.show();
						return;
					}
					// 删除类型及客房成功
					setTypes();
				}

			} catch (RemoteException e) {
				// 网络异常
				e.printStackTrace();
			}
		} else {
			RoomTypeVO vo = typeList
					.get(typeBox.getSelectionModel().getSelectedIndex());
			nameField.setText(vo.roomType);
			nameField.setEditable(false);
			priceField.setText(String.valueOf(vo.price));
			priceField.setEditable(false);
			numLabel.setText(String.valueOf(vo.price));
			introArea.setText(vo.typeIntroduction);
			introArea.setEditable(false);
			modifyButton.setText("修改");
			deleteButton.setText("删除类型");
		}
	}

	public void enter(Parent previous) {
		try {
			this.previous = previous;
			PreOrderVO preOrder = new PreOrderVO();
			preOrder.hotelID = LoginController.getCurrentUser().hotelID;
			typeList = HMSClient.getHotelInfoBL().availableRoomType(preOrder);

		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	void setTypes() {
		for (RoomTypeVO vo : typeList) {
			typeBox.getItems().add(vo.roomType);
		}
		typeBox.getSelectionModel().select(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		numColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<RoomVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<RoomVO, String> param) {
						return new SimpleStringProperty(
								param.getValue().roomNum);
					}
				});
		statusColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<RoomVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<RoomVO, String> param) {
						return new SimpleStringProperty(
								param.getValue().roomStatus.name());
					}
				});
		typeBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						RoomTypeVO vo = typeList.get((Integer) newValue);
						nameField.setText(vo.roomType);
						nameField.setEditable(false);
						priceField.setText(String.valueOf(vo.price));
						priceField.setEditable(false);
						numLabel.setText(String.valueOf(vo.price));
						introArea.setText(vo.typeIntroduction);
						introArea.setEditable(false);
						modifyButton.setText("修改");
						deleteButton.setText("删除类型");
						roomTable.getItems().clear();

						newRoomBox.setText("");
						try {
							roomList = HMSClient.getManageRoomBL()
									.getRoomList(vo.hotelID);
							for (int i = 0; i < roomList.size();) {
								if (i >= roomList.size())
									break;
								if (roomList.get(i).roomType != vo.roomTypeId) {
									roomList.remove(i);
								} else
									i++;
							}
							roomTable.getItems().clear();
							roomTable.getItems().addAll(roomList);
							roomTable.setVisible(false);
							roomTable.setVisible(true);
						} catch (RemoteException e) {
							// 网络异常处理
							e.printStackTrace();
						}
					}
				});
		roomTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<RoomVO>() {
					@Override
					public void changed(
							ObservableValue<? extends RoomVO> observable,
							RoomVO oldValue, RoomVO newValue) {
						deleteButton.setDisable(newValue == null);
					}
				});
	}

}
