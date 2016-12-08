package tiquartet.ClientModule.ui.adminui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.Encryptor;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserInfoUtility;
import tiquartet.CommonModule.vo.UserVO;

public class HotelierSectionController implements Initializable {

	@FXML
	private TableView<UserVO> userListTable;

	@FXML
	private TableColumn<UserVO, String> usernameColumn;

	@FXML
	private TableColumn<UserVO, String> hotelNameColumn;

	@FXML
	private ChoiceBox<String> districtBox;

	@FXML
	private Button modifyButton;

	@FXML
	private ChoiceBox<String> cityBox;

	@FXML
	private Label passwordLabel;

	@FXML
	private ImageView passwordTick;

	@FXML
	private Label confirmPasswordLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private ImageView confirmPasswordTick;
	
	@FXML
    private Label modifyPasswordLabel;

	//当前选择的城市的编号
	private int cityID;
	
	//当前选择的用户
	private UserVO currentSelected;
	
	 /**
     * 显示修改真实姓名的输入区域.
     */
    private void showModifyPassword(){
    	modifyPasswordLabel.setText("为"+currentSelected.userName+"设置新密码");
    	modifyPasswordLabel.setVisible(true);
    	modifyButton.setText("确认");
    	modifyButton.setDisable(true);
    	passwordLabel.setVisible(true);
    	confirmPasswordLabel.setVisible(true);
    	passwordField.setVisible(true);
    	confirmPasswordField.setVisible(true);
    }
    
    /**
     * 隐藏修改真实姓名的输入区域.
     */
    private void hideModifyPassword(){
    	modifyPasswordLabel.setVisible(false);
    	modifyButton.setText("修改密码");
    	modifyButton.setDisable(false);
    	passwordLabel.setVisible(false);
    	confirmPasswordLabel.setVisible(false);
    	passwordField.setVisible(false);
    	confirmPasswordField.setVisible(false);
    }

	@FXML
	void onModifyPassword(ActionEvent event) {
		if(modifyButton.getText().equals("确认")){
			String oldCode = currentSelected.password;
			try {
				currentSelected.password = Encryptor.encript(passwordField.getText());
				ResultMessage message = HMSClient.getManageUserBL().update(currentSelected);
				if(message.result){
					Alert success = new Alert(AlertType.INFORMATION, currentSelected.userName+"的密码修改成功！");
					success.show();
				}
				else{
					Alert fail = new Alert(AlertType.ERROR, currentSelected.userName+"的密码修改失败");
					fail.show();
					currentSelected.realName = oldCode;
				}
				passwordField.setText("");
				confirmPasswordField.setText("");
				hideModifyPassword();
			} catch (RemoteException e) {
				//处理网络异常
				currentSelected.password = oldCode;
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查修改密码输入是否合法.
	 */
	private void checkInput() {
		boolean passwordValid = UserInfoUtility.checkPassword(passwordField.getText());
    	passwordTick.setVisible(passwordValid);
    	boolean confirmValid = confirmPasswordField.getText().equals(passwordField.getText())&&passwordValid;
    	confirmPasswordTick.setVisible(confirmValid);
    	modifyButton.setDisable(!(passwordValid&&confirmValid));
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		districtBox.setDisable(true);
		cityBox.getItems().addAll(DistrictData.getCityMap().values());
		cityBox.getSelectionModel().clearSelection();
		cityID = -1;
		
		usernameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				return new SimpleStringProperty(param.getValue().userName);
			}
			
		});
		hotelNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserVO, String> param) {
				return new SimpleStringProperty(param.getValue().company);
			}
			
		});
		
		userListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		userListTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserVO>() {

			@Override
			public void changed(ObservableValue<? extends UserVO> observable,
					UserVO oldValue, UserVO newValue) {
				currentSelected = newValue;
				if(currentSelected!=null){
					hideModifyPassword();
					modifyButton.setDisable(true);
				}
				else{
					modifyButton.setDisable(false);
				}
			}
		});
		
		// 选择的城市发生变化时相应地改变商圈的选项
		cityBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						String city = cityBox.getItems()
								.get((Integer) newValue);
						cityID = DistrictData.getCityIDOf(city);
						districtBox.getItems().clear();
						districtBox.getItems().add("全部商圈");
						districtBox.getItems().addAll(
								DistrictData.districtNameListOfCity(cityID));
						districtBox.getSelectionModel().clearSelection();
						districtBox.setDisable(false);
					}
				});
		districtBox.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(
					ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
				try {
					String district = districtBox.getItems()
							.get((Integer) newValue);
					//若没有新选中的商圈，直接返回
					if(district==null||district.isEmpty()) return;
					List<UserVO> newList;
					if(district.equals("全部商圈")){
						newList = HMSClient.getManageUserBL().searchHotelStaff(cityID, -1);
					}
					else{
						int index = DistrictData.districtNameListOfCity(cityID).indexOf(district);
						int districtID = DistrictData.districtIDListOfCity(cityID).get(index);
						newList = HMSClient.getManageUserBL().searchHotelStaff(-1, districtID);
					}
					//更新酒店工作人员列表.
					userListTable.getItems().clear();
					userListTable.getItems().addAll(newList);
					userListTable.setVisible(false);
					userListTable.setVisible(true);
				} catch (RemoteException e) {
					//网络异常处理
					e.printStackTrace();
				}
			}
		});

		passwordField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkInput();
					}
				});
		confirmPasswordField.focusedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						checkInput();
					}
				});
		modifyButton.setTooltip(new Tooltip("修改选中用户的密码"));
		modifyButton.setDisable(true);
		passwordField.setTooltip(new Tooltip("6-16个字符，不含空格"));
		confirmPasswordField.setTooltip(new Tooltip("重复新密码以确认"));
	}

}
