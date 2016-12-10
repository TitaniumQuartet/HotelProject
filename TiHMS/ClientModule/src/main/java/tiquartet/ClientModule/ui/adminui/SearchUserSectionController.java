package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.UserFilterVO;
import tiquartet.CommonModule.vo.UserVO;

/**
 * 用户查找界面的控制器.
 * @author greatlyr
 *
 */
public class SearchUserSectionController implements Initializable{

    @FXML
    private Button accuSearchButton;

    @FXML
    private TextField accuUsernameField;

    @FXML
    private Button fuzzySearchButton;

    @FXML
    private TextField fuzzyUsernameField;

    @FXML
    private TextField realNameField;

    @FXML
    private ChoiceBox<String> userTypeBox;
    
    @FXML
    private Label userNotFoundLabel;
    
    public AdminMainController adminMainController;

    @FXML
    void onAccuSearch(ActionEvent event) {
    	try {
			UserVO userVO = HMSClient.getManageUserBL().accurateSearch(accuUsernameField.getText());
			if(userVO==null){
				userNotFoundLabel.setVisible(true);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onFuzzySearch(ActionEvent event) {
    	
    	try {
    		UserFilterVO filterVO = new UserFilterVO(fuzzyUsernameField.getText(), realNameField.getText(), null, null);
        	//对会员身份的筛选可能需要修改
        	
        	switch (userTypeBox.getSelectionModel().getSelectedIndex()) {
    			case 0 :
    				filterVO.userType = UserType.客户;
    				break;
    			case 1 :
    				filterVO.userType = UserType.酒店工作人员;
    				break;
    			case 2 :
    				filterVO.userType = UserType.网站营销人员;
    				break;
    			case 3 :
    				filterVO.userType = null;
    				break;
    		}
			List<UserVO> userList = HMSClient.getManageUserBL().search(filterVO, UserSort.USERNAMEASCEND, 1, 100);
			adminMainController.showSection(adminMainController.filterUser);
			adminMainController.filterUserController.setData(userList);
    	} catch (RemoteException e) {
			//处理网络异常
			e.printStackTrace();
		}
    }

    /**
     * 根据输入框的内容更新按钮状态及提示.
     * 
     */
    private void checkSearch(){
    	String input;
    	
    	//判断精确搜索的用户名输入是否合法
    	input = accuUsernameField.getText();
    	if(input.matches(".[^a-zA-Z0-9_].")||input.length()<6||input.length()>16){
    		accuSearchButton.setDisable(true);
    	}
    	else accuSearchButton.setDisable(false);
    	
    	//若没有选择会员类型，禁用模糊搜索
    	if(userTypeBox.getSelectionModel().isEmpty()) fuzzySearchButton.setDisable(true);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//输入框聚焦状态改变的监听器
		accuUsernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSearch();				
			}
		});
		fuzzyUsernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSearch();				
			}
		});
		realNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkSearch();				
			}
		});
		
		//设置用户类型的选项
		userTypeBox.getItems().addAll("客户","酒店工作人员","网站营销人员","全部类型");
		userTypeBox.getSelectionModel().clearSelection();
		
		userNotFoundLabel.setVisible(false);
	}
    

}
