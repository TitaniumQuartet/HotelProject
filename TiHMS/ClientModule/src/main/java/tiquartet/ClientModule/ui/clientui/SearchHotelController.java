package tiquartet.ClientModule.ui.clientui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.HotelSort;
import tiquartet.CommonModule.vo.HotelBriefVO;
import tiquartet.CommonModule.vo.HotelFilterVO;

public class SearchHotelController implements Initializable {

	@FXML
	private ChoiceBox<Integer> pageNumBox;

	@FXML
	private Button lastPageButton;

	@FXML
	private Button nextPageButton;

	@FXML
	private ChoiceBox<String> starBox;

	@FXML
	private ChoiceBox<String> priceBox;

	@FXML
	private ChoiceBox<String> rateBox;

	@FXML
	private TextField hotelNameField;

	@FXML
	public DatePicker inDateBox;

	@FXML
	public DatePicker outDateBox;

	@FXML
	private MenuItem priceDescend;

	@FXML
	private MenuItem priceAscend;

	@FXML
	private MenuItem rateDescend;

	@FXML
	private MenuItem rateAscend;

	@FXML
	private ScrollPane scroll;

	@FXML
	private GridPane grid;

	@FXML
	private Label resultLabel;

	@FXML
	private Button searchButton;

	public HotelFilterVO filter;

	public HotelSort sort;

	public List<HotelBriefVO> list;

	int page = -1;

	int total = -1;

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
		sort = HotelSort.均价升序;
		page = 1;
		refresh();
	}

	@FXML
	void onPriceDescend(ActionEvent event) {
		sort = HotelSort.均价降序;
		page = 1;
		refresh();
	}

	@FXML
	void onRateAscend(ActionEvent event) {
		sort = HotelSort.评分升序;
		page = 1;
		refresh();
	}

	@FXML
	void onRateDescend(ActionEvent event) {
		sort = HotelSort.评分降序;
		page = 1;
		refresh();
	}

	@FXML
	void onSearchClicked(ActionEvent event) {
		filter.hotelName = hotelNameField.getText();
		if (inDateBox.getValue() != null && outDateBox.getValue() != null) {
			filter.checkInDate = inDateBox.getValue().toString();
			filter.checkOutDate = outDateBox.getValue().toString();
		}
		page = 1;
		refresh();
	}

	public void enter(int districtID) {
		filter = new HotelFilterVO();
		filter.districtID = districtID;
		sort = null;
		starBox.getSelectionModel().select(0);
		rateBox.getSelectionModel().select(0);
		priceBox.getSelectionModel().select(0);
		page = 1;
		refresh();
	}

	public void refresh() {

		try {
			int num = (total >= 0 && total < page * 10)
					? (total - page * 10 + 10)
					: 10;
			list = HMSClient.getSearchHotelBL().getHotelList(filter, sort,
					page * 10 - 9, page * 10 - 9 + num - 1);
			total = list.size();
			resultLabel.setText("找到" + total + "个结果");
			// 设置页码切换组件
			pageNumBox.setDisable(total <= 10);
			lastPageButton.setDisable(page < 2);
			nextPageButton.setDisable(page * 10 >= total);
			if (total > 10) {
				pageNumBox.getItems().clear();
				for (int i = 1; i <= Math.ceil(total / 10.0); i++)
					pageNumBox.getItems().add(i);
			}
			pageNumBox.getSelectionModel().select(new Integer(page));;
			// 设置酒店信息显示
			scroll.setVmax(num <= 10 ? (num + 1) / 2 : 5);
			try {
				for (int i = page * 10 - 9; i <= page * 10 - 9 + num - 1; i++) {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass()
							.getResource("/fxml/clientui/hotelSearched.fxml"));
					Parent parent = loader.load();
					HotelSearchedController controller = loader.getController();
					controller.searchHotelController = this;
					grid.add(parent, i - page * 10 + 9, 0);
					controller.setContent(list.get(i - 1));
				}
			} catch (IOException e) {
				// 界面加载失败
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// 网络连接异常
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		filter = new HotelFilterVO();
		starBox.getItems().addAll("星级不限", "五星级", "四星级", "三星以下");
		rateBox.getItems().addAll("评分不限", "评分8-10", "评分6-8", "评分6以下");
		priceBox.getItems().addAll("价格不限", "￥500以上", "￥300-500", "￥200-300",
				"￥200以下");

		starBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						int i = (Integer) newValue;
						switch (i) {
							case 0 :
								filter.highestStar = -1;
								filter.lowestStar = -1;
								break;
							case 1 :
								filter.highestStar = 5;
								filter.lowestStar = 5;
								break;
							case 2 :
								filter.highestStar = 4;
								filter.lowestStar = 4;
								break;
							case 3 :
								filter.highestStar = 3;
								filter.lowestStar = 0;
								break;
						}
						page = 1;
						refresh();
					}
				});
		rateBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						int i = (Integer) newValue;
						switch (i) {
							case 0 :
								filter.highestGrade = -1;
								filter.lowestGrade = -1;
								break;
							case 1 :
								filter.highestGrade = 10;
								filter.lowestGrade = 8;
								break;
							case 2 :
								filter.highestGrade = 8;
								filter.lowestGrade = 6;
								break;
							case 3 :
								filter.highestGrade = 6;
								filter.lowestGrade = 0;
						}
						page = 1;
						refresh();
					}

				});
		priceBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						int i = (Integer) newValue;
						switch (i) {
							case 0 :
								filter.highestPrice = -1;
								filter.lowestPrice = -1;
								break;
							case 1 :
								filter.highestPrice = -1;
								filter.lowestPrice = 500;
								break;
							case 2 :
								filter.highestPrice = 500;
								filter.lowestPrice = 300;
								break;
							case 3 :
								filter.highestPrice = 300;
								filter.lowestPrice = 200;
								break;
							case 4 :
								filter.highestPrice = 200;
								filter.lowestPrice = -1;
						}
						page = 1;
						refresh();
					}
				});
		pageNumBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Integer>() {

					@Override
					public void changed(
							ObservableValue<? extends Integer> observable,
							Integer oldValue, Integer newValue) {
						if (newValue > 0)
							page = newValue;
						refresh();
					}
				});
	}

}
