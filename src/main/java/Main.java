import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.SessionInstance;

import static java.lang.System.exit;

/**
 * Главный класс для запуска десктоп приложения на JavaFX
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("Course Model UI");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(value -> {
            System.out.println("Bye-bye");
            SessionInstance.getInstance().closeSession();
            exit(0);
        });
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
