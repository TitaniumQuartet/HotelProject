package tiquartet.ClientModule.ui.usermainui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * 主登录界面和客户注册界面的控制器类.
 * @author greatlyr
 *
 */
public class UserMainController {

    @FXML
    private CheckBox rememberBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signInButton;

    @FXML
    private Label loginWarningLabel;

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Button cancelSignInButton;

    @FXML
    private Button finishSignInButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label UsernamePrompt;

    @FXML
    private Label PasswordPrompt;

    @FXML
    private ImageView UsernameSign;

    @FXML
    private ImageView NewPasswordSign;

    @FXML
    private ImageView ConfirmPasswordSign;

    @FXML
    private TextField RealNameField;

    /**
     * 点击主登录界面的登录按钮触发.
     * @param event
     */
    @FXML
    void onLoginCLicked(ActionEvent event) {

    }

    /**
     * 点击主登录界面的注册按钮触发.
     * @param event
     */
    @FXML
    void onSignInClicked(ActionEvent event) {

    }

    /**
     * 点击客户注册界面的取消按钮触发.
     * @param event
     */
    @FXML
    void onCancelSignInClicked(ActionEvent event) {

    }

    /**
     * 点击客户注册界面的注册按钮触发.
     * @param event
     */
    @FXML
    void onFinishSignInClicked(ActionEvent event) {

    }

}
