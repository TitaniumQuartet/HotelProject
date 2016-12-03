package tiquartet.ClientModule.ui.adminui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tiquartet.CommonModule.util.ResultMessage;

/**
 * 酒店管理系统管理员的主页面（左边栏及底部）控制器类
 * @author greatlyr
 *
 */
public class AdminMainController implements Initializable{

    @FXML
    private Hyperlink logoutLink;

    @FXML
    private Hyperlink modifyPasswordLink;

    @FXML
    private ImageView topBarImageView;

    @FXML
    private Button hotelierSectionButton;

    @FXML
    private Button searchSectionButton;

    @FXML
    private Button marketerSectionButton;

    @FXML
    private Button addHotelSectionButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void onLogoutClicked(ActionEvent event) {

    }

    @FXML
    void onModifyPassword(ActionEvent event) {
    	
    }

    @FXML
    void toAddHotelSection(ActionEvent event) {

    }

    @FXML
    void toHotelierSection(ActionEvent event) {

    }

    @FXML
    void toMarketerSection(ActionEvent event) {

    }

    @FXML
    void toSearchSection(ActionEvent event) {
    	showSection("searchSection.fxml");
    }
    
    /**
     * 切换界面右侧主要内容
     * @param fileName 相应内容的FXML文件名
     * @return
     */
    public ResultMessage showSection(String fileName) {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("/fxml/adminui/"+fileName));
    	try {
			Parent section = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(section);
			return new ResultMessage(true);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(false,"界面加载失败",null);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showSection("searchSection.fxml");
	}

}
