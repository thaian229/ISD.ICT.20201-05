import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Path;
import views.screen.splash.SplashScreenHandler;

import java.io.IOException;

public class App extends Application {

    /**
     * Method JavaFx call on start up
     * @param primaryStage {@link Stage} main stage of javaFx application
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            // initialize the scene
            StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Path.BLANK_SCREEN_PATH));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            try {
                SplashScreenHandler splashScreenHandler = new SplashScreenHandler(primaryStage, Path.SPLASH_SCREEN_PATH);
                splashScreenHandler.setScreenTitle(splashScreenHandler.getScreenTitle());
                splashScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Software entry point
     * @param args all environment passed variable
     */
    public static void main(String[] args) {
        launch(args);
    }
}
