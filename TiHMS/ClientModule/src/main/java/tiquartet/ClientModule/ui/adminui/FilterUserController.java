package tiquartet.ClientModule.ui.adminui;

import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.attribute.standard.RequestingUserName;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import tiquartet.CommonModule.util.StringUtility;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;

public class FilterUserController implements Initializable {

    @FXML
    private TableView<UserVO> userListTable;

    @FXML
    private TableColumn<UserVO, String> usernameColumn;

    @FXML
    private TableColumn<UserVO, String> realNameColumn;

    @FXML
    private TableColumn<UserVO, String> userTypeColumn;

    @FXML
    private TableColumn<UserVO, String> memberLevelColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField realNameField;

    @FXML
    private ChoiceBox<String> userTypeBox;

    @FXML
    private CheckBox isMemberBox;

    @FXML
    private Button confirmButton;
    
    private ArrayList<UserVO> userList = new ArrayList<>();

    @FXML
    void onConfirmClicked(ActionEvent event) {
    	//UserFilterVO filterVO = new UserFilterVO(usernameField.getText(), realNameField.getText(), , upperLevel)
    }
    
    public void setData(List<UserVO> users){
    	userList.clear();
    	userList.addAll(users);
    	userListTable.getItems().clear();
    	userListTable.getItems().addAll(userList);
    	
    	usernameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				return new SimpleStringProperty(param.getValue().userName);
			}
			
		});
		usernameColumn.setCellFactory(new Callback<TableColumn<UserVO,String>, TableCell<UserVO,String>>() {
			
			@Override
			public TableCell<UserVO, String> call(TableColumn<UserVO, String> param) {
				HyperLinkCell hyperLinkCell = new HyperLinkCell();
				return hyperLinkCell;
			}
		});
		realNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				return new SimpleStringProperty(param.getValue().realName);
			}
			
		});
		userTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				return new SimpleStringProperty(StringUtility.valueOf(param.getValue().userType));
			}
			
		});
		memberLevelColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				int level = param.getValue().memberLevel;
				return new SimpleStringProperty(level==0?"非会员":String.valueOf(level));
			}
			
		});
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//设置用户类型的选项
		userTypeBox.getItems().addAll("客户","酒店工作人员","网站营销人员","全部类型");
		userTypeBox.getSelectionModel().clearSelection();
	}

}

class HyperLinkCell extends TableCell<UserVO, String>{
	private Hyperlink userLink;
	
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		userLink.setText(item);
	}
	
	public HyperLinkCell(){
		super();
		userLink = new Hyperlink();
		setAlignment(Pos.CENTER);
		setGraphic(userLink);
	}
}
