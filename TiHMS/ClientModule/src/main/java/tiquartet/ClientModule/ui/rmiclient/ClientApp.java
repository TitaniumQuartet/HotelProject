package tiquartet.ClientModule.ui.rmiclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX界面的启动.
 * 
 * @author greatlyr
 *
 */
public class ClientApp extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		HMSClient.setMainStage(primaryStage);
		Scene loginScene = new Scene(
				FXMLLoader
						.load(getClass().getResource("/fxml/usermainui/login.fxml")),
				1280, 800);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("TiHMS 酒店管理系统");
		primaryStage.show();

	}
	
	

}
