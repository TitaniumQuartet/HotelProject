package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

public class ModifyPasswordController implements Initializable{

    @FXML
    private Button confirmModifyButton;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label newPasswordPrompt;

    @FXML
    private ImageView confirmPasswordSign;
    
    @FXML
    private ImageView currentPasswordSign;

    @FXML
    void onConfirmModify(ActionEvent event) {

    }
    
    /**
     * 用于在任意输入框失去焦点后更新按钮状态并提供提示.
     * 
     */
    private void checkPassword(){
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
		newPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				checkPassword();				
			}
		});
	}

}
