package org.genfork.test_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class ClientApp extends Application {
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(ClientApp.class.getResource("/Client_scene1.fxml"));
		final Parent p = loader.load();
		final Scene scene = new Scene(p);

		primaryStage.setScene(scene);

		final String title_window = "2017 (c) GenCloud";
		final String icon = ClientApp.class.getResource("/images/icon.png").toExternalForm();
		primaryStage.setTitle(title_window);
		primaryStage.getIcons().add(new Image(icon));

		primaryStage.show();
	}
}
