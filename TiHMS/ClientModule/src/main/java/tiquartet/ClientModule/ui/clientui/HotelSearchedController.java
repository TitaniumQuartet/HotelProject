package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tiquartet.ClientModule.ui.customnode.RoomTypePane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.RoomTypeVO;

public class HotelSearchedController implements Initializable {

	@FXML
	private ImageView hotelImage;

	@FXML
	private Label hotelNameLabel;

	@FXML
	private ImageView starImage;

	@FXML
	private Label rateLabel;

	@FXML
	private Label introLabel;

	@FXML
	private Label orderNumLabel;

	@FXML
	private ScrollPane scroll;

	@FXML
	private HBox roomTypeHBox;

	@FXML
	private Label contactLabel;

	@FXML
	private TextField contactField;

	@FXML
	private Button createOrderLabel;

	@FXML
	private ChoiceBox<Integer> roomNumBox;

	@FXML
	void onCreateOrder(ActionEvent event) {

	}

	public SearchHotelController searchHotelController;

	public HotelBriefVO hotelBriefVO;

	ArrayList<RoomTypeVO> typeList = new ArrayList<>();

	PreOrderVO preOrder = new PreOrderVO();

	ToggleGroup radioGroup = new ToggleGroup();

	void showTypes() {
		for (Node node : roomTypeHBox.getChildren()) {
			RoomTypePane pane = (RoomTypePane) node;
			pane.button.setToggleGroup(null);
		}
		roomTypeHBox.getChildren().clear();
		for (RoomTypeVO vo : typeList) {
			roomTypeHBox.getChildren().add(new RoomTypePane(vo));
		}
		for (Node node : roomTypeHBox.getChildren()) {
			RoomTypePane pane = (RoomTypePane) node;
			pane.button.setToggleGroup(radioGroup);
		}
	}

	public void setContent(HotelBriefVO briefVO) {
		hotelBriefVO = briefVO;
		hotelNameLabel.setText(briefVO.hotelName);
		starImage.setImage(
				new Image("/image/clientui/" + briefVO.star + "star.jpg"));
		rateLabel.setText(String.format("%.1f", briefVO.averageGrade));
		introLabel.setText(briefVO.introduction);
		orderNumLabel.setText("我共有" + briefVO.numOfAllOrder + "笔订单，已执行"
				+ briefVO.numOfExecutedOrder + "笔");
		contactLabel.setVisible(false);
		contactField.setVisible(false);
		createOrderLabel.setDisable(true);
		preOrder.startTime = searchHotelController.inDateBox.getValue()
				.toString();
		preOrder.leaveTime = searchHotelController.outDateBox.getValue()
				.toString();
		preOrder.hotelID = hotelBriefVO.hotelID;
		preOrder.numOfRoom = 1;
		preOrder.userID = LoginController.getCurrentUser().userID;
		try {
			List<RoomTypeVO> list = HMSClient.getHotelInfoBL()
					.availableRoomType(preOrder);
			typeList.clear();
			typeList.addAll(list);
			showTypes();
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 1; i <= 10; i++)
			roomNumBox.getItems().add(i);
		roomNumBox.getSelectionModel().clearSelection();
		roomNumBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						try {
							preOrder.startTime = searchHotelController.inDateBox
									.getValue().toString();
							preOrder.leaveTime = searchHotelController.outDateBox
									.getValue().toString();
							preOrder.hotelID = hotelBriefVO.hotelID;
							preOrder.numOfRoom = (Integer) newValue;
							List<RoomTypeVO> list = HMSClient.getHotelInfoBL()
									.availableRoomType(preOrder);
							typeList.clear();
							typeList.addAll(list);
							showTypes();

						} catch (RemoteException e) {
							// 网络异常处理
							e.printStackTrace();
						}
					}
				});
		radioGroup.selectedToggleProperty()
				.addListener(new ChangeListener<Toggle>() {

					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						boolean chosen = newValue != null
								&& radioGroup.getSelectedToggle() != null;
						contactLabel.setVisible(chosen);
						if (chosen && !contactField.isVisible())
							contactField.setText(
									LoginController.getCurrentUser().phone);
						contactField.setVisible(chosen);

						createOrderLabel.setVisible(chosen);
					}
				});
	}

}
