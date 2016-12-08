package tiquartet.ClientModule.ui.adminui;

import java.util.List;
import java.net.URL;
import java.rmi.RemoteException;
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
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.StringUtility;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.util.UserType;
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
    private ChoiceBox<String> isMemberBox;
    
    @FXML
    private Button confirmButton;
    
    private ArrayList<UserVO> userList = new ArrayList<>();

    @FXML
    void onConfirmClicked(ActionEvent event) {
    	UserFilterVO filterVO = new UserFilterVO(usernameField.getText(), realNameField.getText(), null, null);
    	switch(isMemberBox.getSelectionModel().getSelectedIndex()) {
    		case 0 :
				filterVO.memberType = null;
				break;
    		case 1 :
				filterVO.memberType = MemberType.NOTMEMBER;
				break;
    		case 2 :
				filterVO.memberType = MemberType.PERSONMEMBER;
				break;
    		case 3 :
				filterVO.memberType = MemberType.COMPANYMEMBER;
				break;
    	}
    	switch (userTypeBox.getSelectionModel().getSelectedIndex()) {
			case 0 :
				filterVO.userType = UserType.CLIENT;
				break;
			case 1 :
				filterVO.userType = UserType.HOTELSTAFF;
				break;
			case 2 :
				filterVO.userType = UserType.MARKETER;
				break;
			case 3 :
				filterVO.userType = null;
				break;
		}
    	try {
			List<UserVO> list = HMSClient.getManageUserBL().search(filterVO, UserSort.USERNAMEASCEND, 1, 50);
			/*
			 * 待添加对翻页的处理
			 */
			setData(list);
		} catch (RemoteException e) {
			// 网络异常处理
			e.printStackTrace();
		}
    }
    
    public void setData(List<UserVO> users){
    	userList.clear();
    	userList.addAll(users);
    	userListTable.getItems().clear();
    	userListTable.getItems().addAll(userList);
    	
    	userListTable.setVisible(false);
    	userListTable.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isMemberBox.getItems().addAll("会员和非会员","非会员","个人会员","企业会员");
		isMemberBox.getSelectionModel().select(0);
		
		//设置用户类型的选项
		userTypeBox.getItems().addAll("客户","酒店工作人员","网站营销人员","全部类型");
		userTypeBox.getSelectionModel().clearSelection();
		
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
