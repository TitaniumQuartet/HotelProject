package tiquartet.ClientModule.ui.adminui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FilterUserController implements Initializable {

    @FXML
    private TableView<?> userListTable;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private TableColumn<?, ?> realNameColumn;

    @FXML
    private TableColumn<?, ?> userTypeColumn;

    @FXML
    private TableColumn<?, ?> memberLevelColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField realNameField;

    @FXML
    private ChoiceBox<?> userTypeBox;

    @FXML
    private CheckBox isMemberBox;

    @FXML
    private Button confirmButton;

    @FXML
    void onConfirmClicked(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
