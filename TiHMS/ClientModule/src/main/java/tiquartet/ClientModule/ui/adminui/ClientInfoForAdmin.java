package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import tiquartet.ClientModule.ui.datastorage.MemberLevelData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

/**
 * 网站管理人员查看、修改单个用户的信息.
 * 可以增加修改密码的功能
 * @author greatlyr
 *
 */
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
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private TextField input3;

    @FXML
    private TextField input4;

    @FXML
    private TextField input5;
    
    @FXML
    private DatePicker datePicker;

    @FXML
    private Label titleLabel;
    
    @FXML
    private Label resultLabel;
    
    private UserVO userInfo;
    
    /**
     * 检查输入，更新按钮状态.
     * 
     */
    private void checkInput(){
    	boolean inputValid = true;
    	
    	if(userInfo.birthday!=null&&!userInfo.birthday.isEmpty()){
    		LocalDate date = datePicker.getValue();
    		if(date==null) inputValid = false;
    	}
    	else{
    		if(input5.getText().isEmpty()) inputValid = false;
    	}
    	if(inputValid) modifyButton.setDisable(false);
    	else modifyButton.setDisable(true);
    	
    	resultLabel.setVisible(false);
    }
    

    @FXML
    void onModifyButtonClicked(ActionEvent event) {
    	if(modifyButton.getText().equals("修改")){
    		//显示真实姓名输入框
    		data1.setVisible(false);
    		input1.setText(data1.getText());
    		input1.setVisible(true);
    		
    		//显示会员生日/公司名称输入框                
    		data4.setVisible(false);
    		
    		if(userInfo.company!=null&&!userInfo.company.isEmpty()){
    			input4.setText(data4.getText());
    			input4.setVisible(true);
    		}
    		else{
    			datePicker.setVisible(true);
    		}
    		modifyButton.setText("确认");
    		modifyButton.setDisable(false);
    	}
    	else if(modifyButton.getText().equals("确认")){
    		String real = userInfo.realName, bday =userInfo.birthday, co = userInfo.company;
    		userInfo.realName = input1.getText();
    		if(input4.isVisible()){
    			userInfo.company=input4.getText();
    		}
    		else{
    			String dateString = new SimpleDateFormat("yyyy-MM-dd").format(datePicker.getValue());
    			userInfo.birthday = dateString;
    		}
    		try {
				ResultMessage message = HMSClient.getManageUserBL().update(userInfo);
				if(message.result){
					resultLabel.setText("保存成功！");
					resultLabel.setVisible(true);
					input1.setVisible(false);
					input4.setVisible(false);
					datePicker.setVisible(false);
					data1.setText(userInfo.realName);
					data1.setVisible(true);
					data4.setText(field4.equals("生日")?userInfo.birthday:userInfo.company);
					data4.setVisible(true);
					modifyButton.setText("确认");
				}
				else{
					userInfo.realName = real;
					userInfo.birthday = bday;
					userInfo.company = co;
					resultLabel.setText("保存失败");
					resultLabel.setVisible(true);
				}
				
			} catch (RemoteException e) {
				userInfo.realName = real;
				userInfo.birthday = bday;
				userInfo.company = co;
				resultLabel.setText("无法连接服务器");
				resultLabel.setVisible(true);
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * 根据传入的用户信息修改界面内容.
     * @param clientInfo
     */
    public void setData(UserVO clientInfo){
    	userInfo = clientInfo;
    	
    	titleLabel.setText("客户"+clientInfo.userName+"详细信息");
    	
    	data1.setText(clientInfo.realName);
    	data1.setVisible(true);
    	
    	data2.setText(String.valueOf(clientInfo.credit));
    	data2.setVisible(true);
    	
    	if(clientInfo.memberType==MemberType.非会员) data3.setText(MemberLevelData.getLevelName(0));
    	else {
    		//用户是会员
    		data3.setText(MemberLevelData.getLevelName(clientInfo.memberLevel));
    		if(clientInfo.birthday!=null&&!clientInfo.birthday.isEmpty()){
    			//个人会员，填写了生日
    			field4.setText("生日");
    			data4.setText(clientInfo.birthday);
    		}
    		else{
    			//企业会员，填写了公司名称
    			field4.setText("公司名称");
    			data4.setText(clientInfo.company);
    		}
    		field4.setVisible(true);
    		data4.setVisible(true);
    	}
    	data3.setVisible(true);
    	
    	input1.setTooltip(new Tooltip("修改用户真实姓名"));
    	input4.setTooltip(new Tooltip("修改会员信息"));
    	datePicker.setTooltip(new Tooltip("请选择出生日期"));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	field1.setText("真实姓名");
    	field1.setVisible(true);
    	
    	field2.setText("信用值");
    	field2.setVisible(true);
    	
    	field3.setText("会员等级");
    	field3.setVisible(true);
    	
    	input1.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});    	
    	input2.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
    	input3.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
    	input4.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
    	input5.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
    	datePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkInput();				
			}
		});
    	
    	input1.setTooltip(new Tooltip("客户的真实姓名"));
    	input4.setTooltip(new Tooltip("会员的必要信息"));
    	
	}

}

