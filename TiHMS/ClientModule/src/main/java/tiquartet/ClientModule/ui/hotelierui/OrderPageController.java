package tiquartet.ClientModule.ui.hotelierui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.customnode.OrderDetailsPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderVO;

public class OrderPageController implements Initializable {

	@FXML
	private ChoiceBox<Integer> pageNumBox;

	@FXML
	private Button lastPageButton;

	@FXML
	private Button nextPageButton;

	@FXML
	private ChoiceBox<String> priceBox;

	@FXML
	private DatePicker startDateBox;

	@FXML
	private DatePicker endDateBox;

	@FXML
	private MenuItem priceDescend;

	@FXML
	private MenuItem priceAscend;

	@FXML
	private MenuItem createAscend;

	@FXML
	private MenuItem createDescend;

	@FXML
	private MenuItem checkInAscend;

	@FXML
	private MenuItem checkInDescend;

	@FXML
	private Button searchButton;

	@FXML
	private TextField guestBox;

	@FXML
	private Label resultLabel;

	@FXML
	private GridPane grid;

	@FXML
	private ChoiceBox<String> statusBox;

	public OrderFilterVO filter;

	public OrderSort sort;

	/**
	 * 当前订单列表的页号.
	 */
	public int page;

	/**
	 * 订单搜索结果总数.
	 */
	public int total;

	@FXML
	void onLastPage(ActionEvent event) {
		page--;
		refresh();
	}

	@FXML
	void onNextPage(ActionEvent event) {
		page++;
		refresh();
	}

	@FXML
	void onPriceAscend(ActionEvent event) {
		page = 1;
		sort = OrderSort.订单总价升序;
	}

	@FXML
	void onPriceDescend(ActionEvent event) {
		page = 1;
		sort = OrderSort.订单总价降序;
	}

	@FXML
	void onCheckInAscend(ActionEvent event) {
		page = 1;
		sort = OrderSort.入住日期升序;
	}

	@FXML
	void onCheckInDescend(ActionEvent event) {
		page = 1;
		sort = OrderSort.入住日期降序;
	}

	@FXML
	void onCreateAscend(ActionEvent event) {
		page = 1;
		sort = OrderSort.生成日期升序;
	}

	@FXML
	void onCreateDescend(ActionEvent event) {
		page = 1;
		sort = OrderSort.生成日期降序;
	}

	@FXML
	void onSearchClicked(ActionEvent event) {
		page = 1;
		int index = priceBox.getSelectionModel().getSelectedIndex();
		switch (index) {
			case 0 :
				filter.lowprice = -1;
				filter.highprice = -1;
				break;
			case 1 :
				filter.lowprice = 1000;
				filter.highprice = -1;
				break;
			case 2 :
				filter.lowprice = 500;
				filter.highprice = 1000;
				break;
			case 3 :
				filter.lowprice = 300;
				filter.highprice = 500;
				break;
			case 4 :
				filter.lowprice = -1;
				filter.highprice = 300;
				break;
		}
		filter.guestRealName = guestBox.getText();
		filter.startTime = startDateBox.getValue().toString() + " 00:00:00";
		filter.endTime = endDateBox.getValue().toString() + " 23:59:59";
		if (statusBox.getSelectionModel().getSelectedIndex() >= 0
				&& statusBox.getSelectionModel().getSelectedIndex() <= 3)
			filter.orderState = OrderStatus.values()[statusBox
					.getSelectionModel().getSelectedIndex()];
		else if (statusBox.getSelectionModel().getSelectedIndex() == 4)
			filter.orderState = OrderStatus.线下已执行订单;
		else
			filter.orderState = null;
		refresh();
	}

	/**
	 * 改变订单列表内容.
	 */
	public void refresh() {
		try {
			List<OrderVO> list = HMSClient.getManageOrderBL()
					.orderHistory(filter, sort, page * 6 - 5, page * 6);
			if (total != list.size()) {
				total = list.size();
				pageNumBox.getItems().clear();
				for (int i = 1; i <= Math.ceil(total / 6.0); i++)
					pageNumBox.getItems().add(i);
			}
			resultLabel.setText("找到" + total + "笔订单");
			pageNumBox.getSelectionModel().select(new Integer(page));
			lastPageButton.setDisable(page < 2);
			nextPageButton.setDisable(page >= Math.ceil(total / 6.0));
			for (int i = page * 6 - 5; i <= Math.min(page * 6, total); i++) {
				grid.add(new OrderDetailsPane(list.get(i - 1)),
						(i + 5 - page * 6) % 3, (i + 5 - page * 6) / 3);
			}
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	/**
	 * 切换至订单列表界面时执行的方法.
	 */
	public void enter(OrderFilterVO filterVO) {
		if (filterVO == null)
			filter = new OrderFilterVO();
		else
			filter = filterVO;
		filter.userId = LoginController.getCurrentUser().userID;
		sort = OrderSort.生成日期降序;
		page = 1;

		priceBox.getSelectionModel().clearSelection();
		startDateBox.setValue(null);
		endDateBox.setValue(null);
		guestBox.setText("");

		refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		priceBox.getItems().addAll("总价不限", "￥1000以上", "￥500-1000", "￥300-500",
				"￥300以下");
		ArrayList<String> statusList = new ArrayList<>();
		for (int i = 0; i < OrderStatus.values().length; i++) {
			if (i == 3)
				continue;// 跳过暂时预定类型
			statusList.add(OrderStatus.values()[i].name());
		}
		statusBox.getItems().addAll(statusList);
		statusBox.getItems().add("全部");
		sort = null;
	}

}
