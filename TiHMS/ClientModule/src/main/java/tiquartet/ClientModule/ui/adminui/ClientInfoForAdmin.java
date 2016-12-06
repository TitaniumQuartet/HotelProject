package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import tiquartet.ClientModule.ui.datastorage.MemberLevelData;
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
    void onModifyButtonClicked(ActionEvent event) {
    	if(modifyButton.getText().equals("修改")){
    		//显示真实姓名输入框
    		data2.setVisible(false);
    		input2.setText(data2.getText());
    		input2.setVisible(true);
    		input2.setTooltip(new Tooltip("修改用户真实姓名"));
    		//显示会员生日/公司名称输入框
    		data5.setVisible(false);
    		input5.setText(data5.getText());
    		input5.setVisible(true);
    		input5.setTooltip(new Tooltip("修改会员"+field5.getText()));
    		modifyButton.setText("确认");
    	}
    	else if(modifyButton.getText().equals("确认")){
    		
    	}
    }
    
    public void setData(UserVO clientInfo){
    	data1.setText(clientInfo.userName);
    	
    	data2.setText(clientInfo.realName);
    	data2.setVisible(true);
    	
    	data3.setText(String.valueOf(clientInfo.credit));
    	data3.setVisible(true);
    	
    	if(!clientInfo.isMember) data4.setText(MemberLevelData.getLevelName(0));
    	else {
    		//用户是会员
    		data4.setText(MemberLevelData.getLevelName(clientInfo.memberLevel));
    		if(clientInfo.birthday!=null&&!clientInfo.birthday.isEmpty()){
    			//个人会员，填写了生日
    			field5.setText("生日");
    			data5.setText(clientInfo.birthday);
    		}
    		else{
    			//企业会员，填写了公司名称
    			field5.setText("公司名称");
    			data5.setText(clientInfo.company);
    		}
    		field5.setVisible(true);
    		data5.setVisible(true);
    	}
    	data4.setVisible(true);
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		titleLabel.setText("用户详细信息");
		
		field1.setVisible(true);
    	data1.setVisible(true);
    	
    	field2.setText("真实姓名");
    	field2.setVisible(true);
    	
    	
    	field3.setText("信用值");
    	field3.setVisible(true);
    	
    	field4.setText("会员等级");
    	field4.setVisible(true);
    	
	}

}

