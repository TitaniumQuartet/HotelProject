package tiquartet.ClientModule.ui.clientui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.ClientModule.ui.usermainui.LoginController;
import tiquartet.CommonModule.vo.CreditVO;

public class CreditRecordController implements Initializable {

	@FXML
	private TableView<CreditVO> table;

	@FXML
	private TableColumn<CreditVO, String> typeColumn;

	@FXML
	private TableColumn<CreditVO, String> changeColumn;

	@FXML
	private TableColumn<CreditVO, String> balanceColumn;

	@FXML
	private TableColumn<CreditVO, String> orderIDColumn;

	@FXML
	private TableColumn<CreditVO, String> timeColumn;

	@FXML
	private Button quitButton;

	Parent previous;

	@FXML
	void onQuitClicked(ActionEvent event) {
		HMSClient.clientMainController.showSimply(previous);
	}

	public void enter(Parent previous) {
		this.previous = previous;
		List<CreditVO> list = new ArrayList<>();
		try {
			list = HMSClient.getManageUserBL()
					.getCreditRecord(LoginController.getCurrentUser().userID);
			table.getItems().clear();
			table.getItems().addAll(list);
			table.setVisible(false);
			table.setVisible(true);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<CreditVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<CreditVO, String> param) {
						return new SimpleStringProperty(
								param.getValue().changeType.name());
					}
				});
		changeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<CreditVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<CreditVO, String> param) {
						return new SimpleStringProperty(
								String.format("%.2d", param.getValue().change));
					}
				});
		balanceColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<CreditVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<CreditVO, String> param) {
						return new SimpleStringProperty(String.format("%.2d",
								param.getValue().balance));
					}
				});
		orderIDColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<CreditVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<CreditVO, String> param) {
						return new SimpleStringProperty(
								(param.getValue().orderID == -1)
										? ""
										: String.valueOf(
												param.getValue().orderID));
					}
				});
		timeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<CreditVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<CreditVO, String> param) {
						return new SimpleStringProperty(param.getValue().time);
					}
				});
	}

}
