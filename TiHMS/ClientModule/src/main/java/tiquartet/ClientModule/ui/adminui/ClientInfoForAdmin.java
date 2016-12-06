package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tiquartet.CommonModule.vo.UserVO;

public class ClientInfoForAdmin implements Initializable {

    @FXML
    private Label field1;

    @FXML
    private Label data1;

    @FXML
    private Label field2;

    @FXML
    private Label data2;

    @FXML
    private Label field3;

    @FXML
    private Label data3;

    @FXML
    private Label field4;

    @FXML
    private Label field5;

    @FXML
    private Label data4;

    @FXML
    private Label data5;

    @FXML
    private Button modifyButton;

    @FXML
    private TextField input2;

    @FXML
    private TextField input3;

    @FXML
    private TextField input4;

    @FXML
    private TextField input5;

    @FXML
    private Label titleLabel;
    
    @FXML
    private Label resultLabel;
    
    /**
     * 根据传入的用户信息修改界面内容.
     * @param userVO
     */
    public void setUserData(UserVO userVO){
    	
    }

    @FXML
    void onSignUpClicked(ActionEvent event) {
    	
    }
    
    public void setData(UserVO clientInfo){
    	data1.setText(clientInfo.userName);
    	
    	data2.setText(clientInfo.realName);
    	data2.setVisible(true);
    	
    	if(clientInfo.isMember){
    		
    	}
    	data3.setVisible(true);
    	
    	//data4.setText(value);
    	data4.setVisible(true);
    	
    	//data5.setText(value);
    	data5.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		titleLabel.setText("用户详细信息");
		
		field1.setVisible(true);
    	data1.setVisible(true);
    	
    	field2.setText("真实姓名");
    	field2.setVisible(true);
    	
    	
    	field3.setText("会员等级");
    	field3.setVisible(true);
    	
    	field4.setText("信用值");
    	field4.setVisible(true);
    	
	}

}

