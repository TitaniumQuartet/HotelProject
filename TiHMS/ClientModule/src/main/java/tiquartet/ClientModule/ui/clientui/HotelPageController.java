package tiquartet.ClientModule.ui.clientui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import tiquartet.ClientModule.ui.customnode.OrderBriefPane;
import tiquartet.ClientModule.ui.customnode.ReviewPane;
import tiquartet.ClientModule.ui.customnode.RoomTypePane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.HotelDetailsVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.ReviewVO;
import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.UserVO;

public class HotelPageController implements Initializable {

	@FXML
	private SplitPane split;

	@FXML
	private Button myOrdersButton;

	@FXML
	private FlowPane orderFlowPane;

	@FXML
	private FlowPane reviewFlowPane;

	@FXML
	private Button writeReviewButton;

	@FXML
	private Label hotelNameLabel;

	@FXML
	private ImageView starImage;

	@FXML
	private Label locationLabel;

	@FXML
	private HBox roomTypeHBox;

	@FXML
	private FlowPane roomTypeFlowPane;

	@FXML
	private ImageView hotelImage;

	@FXML
	private Label hotelIntroLabel;

	@FXML
	private Label serviceIntroLabel;

	@FXML
	private DatePicker inDateBox;

	@FXML
	private DatePicker outDateBox;

	@FXML
	private Label roomNumLabel;

	@FXML
	private ChoiceBox<Integer> roomNumBox;

	@FXML
	private Button createOrderButton;

	public ClientMainController clientMainController;

	public HotelDetailsVO hotelDetailsVO;

	PreOrderVO preOrder;

	ToggleGroup group = new ToggleGroup();

	List<RoomTypeVO> types;

	@FXML
	void onCreateOrder(ActionEvent event) {
		UserVO userVO = LoginController.getCurrentUser();
		preOrder.clientRealName = userVO.userName;
		preOrder.hotelID = hotelDetailsVO.hotelID;
		preOrder.hotelName = hotelDetailsVO.hotelName;
		preOrder.leaveTime = outDateBox.getValue().toString();
		preOrder.numOfRoom = roomNumBox.getSelectionModel().getSelectedItem();
		preOrder.phone = userVO.phone;
		RoomTypeVO chosen = types
				.get(group.getToggles().indexOf(group.getSelectedToggle()));
		preOrder.price = chosen.price
				* roomNumBox.getSelectionModel().getSelectedItem();
		preOrder.roomType = chosen.roomTypeId;
		preOrder.roomTypeName = chosen.roomType;
		preOrder.startTime = inDateBox.getValue().toString();
		preOrder.userID = userVO.userID;
		preOrder.userName = userVO.userName;
		try {
			ResultMessage message = HMSClient.getCreateOrderBL()
					.preOrder(preOrder);
			if (message.result) {
				HMSClient.clientMainController.showCreateOrder(
						Long.parseLong(message.message), inDateBox.getValue(),
						HMSClient.clientMainController.hotelDetails);
			} else {
				Alert alert = new Alert(AlertType.ERROR, message.failInfo);
				alert.show();
			}
		} catch (Exception e) {
			// 网络连接错误
		}
	}

	@FXML
	void toMyOrders(ActionEvent event) {
		clientMainController.showMyOrders();
	}

	@FXML
	void writeReview(ActionEvent event) {
		clientMainController.showReviewDialog(hotelDetailsVO.hotelID);
	}

	/**
	 * 设置要显示的酒店信息，并设置日期
	 * 
	 * @param hotelID
	 * @param inDate
	 * @param outDate
	 */
	public void setHotel(int hotelID, LocalDate inDate, LocalDate outDate) {
		try {
			hotelDetailsVO = HMSClient.getHotelInfoBL().getHotelDetails(hotelID,
					LoginController.getCurrentUser().userID);
			if (hotelDetailsVO == null)
				return;
			hotelNameLabel.setText(hotelDetailsVO.hotelName);
			starImage.setImage(new Image(
					"/image/clientui/" + hotelDetailsVO.star + "star.jpg"));
			locationLabel.setText(
					hotelDetailsVO.cityName + "，" + hotelDetailsVO.circleName
							+ "\n" + hotelDetailsVO.address);
			hotelIntroLabel.setText(hotelDetailsVO.introduction);
			serviceIntroLabel.setText(hotelDetailsVO.serviceintro);

			orderFlowPane.getChildren().clear();
			for (OrderVO order : hotelDetailsVO.orderList) {
				orderFlowPane.getChildren().add(new OrderBriefPane(order));
			}
			reviewFlowPane.getChildren().clear();
			for (ReviewVO review : hotelDetailsVO.reviewList) {
				reviewFlowPane.getChildren().add(new ReviewPane(review));
			}
			inDateBox.setValue(inDate);
			outDateBox.setValue(outDate);
			roomNumBox.getItems().clear();
			roomNumBox.setDisable(true);
			createOrderButton.setDisable(true);
			roomTypeFlowPane.getChildren().clear();
			preOrder = new PreOrderVO();
			preOrder.startTime = inDate.toString();
			preOrder.leaveTime = outDate.toString();
			preOrder.hotelID = hotelDetailsVO.hotelID;
			preOrder.numOfRoom = 1;
			preOrder.userID = LoginController.getCurrentUser().userID;
			setTypes();

		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	/**
	 * 日期分别默认设为明天和后天.
	 * 
	 * @param hotelID
	 */
	public void setHotel(int hotelID) {
		setHotel(hotelID, LocalDate.now().plusDays(1),
				LocalDate.now().plusDays(2));
	}

	/**
	 * 更新房间类型的显示.
	 */
	public void setTypes() {
		try {
			group.getToggles().clear();
			types = HMSClient.getHotelInfoBL().availableRoomType(preOrder);
			for (RoomTypeVO typeVO : types) {
				RoomTypePane pane = new RoomTypePane(typeVO);
				roomTypeFlowPane.getChildren().add(pane);
				group.getToggles().add(pane.button);
			}
		} catch (RemoteException e) {
			// 网络连接异常
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inDateBox.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if (newValue.compareTo(outDateBox.getValue()) >= 0) {
					roomTypeFlowPane.getChildren().clear();
					return;
				}
				preOrder.startTime = newValue.toString();
				setTypes();
			}
		});
		outDateBox.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if (newValue.compareTo(inDateBox.getValue()) <= 0) {
					roomTypeFlowPane.getChildren().clear();
					return;
				}
				preOrder.leaveTime = newValue.toString();
				setTypes();
			}
		});
		group.selectedToggleProperty()
				.addListener(new ChangeListener<Toggle>() {

					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						roomNumBox.setDisable(newValue == null);
						createOrderButton.setDisable(newValue == null);
						if (newValue == null)
							return;
						int index = group.getToggles().indexOf(newValue);
						for (int i = 0; i < types.get(index).number; i++)
							roomNumBox.getItems().add(i);
						roomNumBox.getSelectionModel().select(0);
					}
				});
	}

}
