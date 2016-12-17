package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.customnode.HotelBriefPane;
import tiquartet.ClientModule.ui.customnode.OrderBriefPane;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderVO;

public class HomePageController implements Initializable {

	@FXML
	private AnchorPane mianPane;

	@FXML
	private ChoiceBox<String> districtBox;

	@FXML
	private Button searchHotelButton;

	@FXML
	private ChoiceBox<String> cityBox;

	@FXML
	private GridPane hotelGridPane;

	@FXML
	private GridPane orderGridPane;

	@FXML
	private Button myHotelsButton;

	@FXML
	private Button myOrdersButton;

	private int cityID = -1;

	public ClientMainController clientMainController;

	@FXML
	void onSearchHotel(ActionEvent event) {
		if (districtBox.getSelectionModel().getSelectedIndex() >= 0) {
			try {
				HotelFilterVO filterVO = new HotelFilterVO();
				String districtName = districtBox.getItems().get(
						districtBox.getSelectionModel().getSelectedIndex());
				int districtID = DistrictData.districtIDListOfCity(cityID)
						.get(DistrictData.districtNameListOfCity(cityID)
								.indexOf(districtName));
				filterVO.districtID = districtID;
				List<HotelBriefVO> hotelList = HMSClient.getSearchHotelBL()
						.getHotelList(filterVO, HotelSort.评分降序, 1, 6);
			} catch (RemoteException e) {
				// 处理网络异常
				e.printStackTrace();
			}
		}
	}

	@FXML
	void toMyHotels(ActionEvent event) {
		clientMainController.showMyHotelList();
	}

	@FXML
	void toMyOrders(ActionEvent event) {
		clientMainController.showMyOrders();
	}

	public void setContent() {
		try {
			List<HotelBriefVO> hotelList = HMSClient.getSearchHotelBL()
					.recommend(LoginController.getCurrentUser().userID);
			for (int i = 0; i < 2; i++)
				hotelGridPane.add(new HotelBriefPane(hotelList.get(i)), 0, i);

			OrderFilterVO orderFilter = new OrderFilterVO();
			orderFilter.userName = LoginController.getCurrentUser().userName;
			List<OrderVO> orderList = HMSClient.getManageOrderBL()
					.orderHistory(orderFilter, OrderSort.生成日期降序, 1, 3);
			for (int i = 0; i < 3; i++)
				orderGridPane.add(new OrderBriefPane(orderList.get(i)), 0, i);
		} catch (RemoteException e) {
			// 处理网络异常
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		districtBox.setDisable(true);
		cityBox.getItems().addAll(DistrictData.getCityMap().values());
		cityBox.getSelectionModel().clearSelection();
		cityID = -1;

		searchHotelButton.setTooltip(new Tooltip("请选择酒店所在的城市和商圈"));

		// 选择的城市发生变化时相应地改变商圈的选项
		cityBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						if ((Integer) newValue == -1) {
							districtBox.setDisable(true);
							return;
						} else
							districtBox.setDisable(false);
						String city = cityBox.getItems()
								.get((Integer) newValue);
						cityID = DistrictData.getCityIDOf(city);
						districtBox.getItems().clear();
						districtBox.getItems().addAll(
								DistrictData.districtNameListOfCity(cityID));
						districtBox.getSelectionModel().clearSelection();
						districtBox.setDisable(false);
					}
				});
	}

}
