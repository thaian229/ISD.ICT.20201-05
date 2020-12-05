import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.splash.SplashScreenHandler;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            // initialize the scene
            StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Configs.BLANK_SCREEN_PATH));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            try {
                SplashScreenHandler splashScreenHandler = new SplashScreenHandler(primaryStage, Configs.SPLASH_SCREEN_PATH);
                splashScreenHandler.setScreenTitle(splashScreenHandler.getScreenTitle());
                splashScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
