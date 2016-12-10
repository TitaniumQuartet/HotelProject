package tiquartet.ClientModule.ui.rmiclient;

import java.io.IOException;

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
	public void start(Stage primaryStage) {
		HMSClient.setMainStage(primaryStage);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/clientui/clientMain.fxml"));
			Scene loginScene = new Scene(loader.load(), 1280, 800);
			//HMSClient.adminMainController = loader.getController();
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("TiHMS 酒店管理系统");
			primaryStage.show();
		} catch (IOException e) {
			System.out.println("界面加载失败");
			e.printStackTrace();
		}
		
	}
	
	

}
