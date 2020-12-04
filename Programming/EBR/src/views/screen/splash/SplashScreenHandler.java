package views.screen.splash;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    ImageView bigLogo;

    @FXML
    Button start;

    public SplashScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("assets/images/BigLOGO.png");
        Image image = new Image(file.toURI().toString());
        System.out.println(image.getHeight());
        bigLogo.setImage(image);

        start.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                HomeScreenHandler homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
                homeScreenHandler.setScreenTitle("Home Screen");
                homeScreenHandler.setImage();
                homeScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void setImage() {
        File file = new File("assets/images/BigLOGO.png");
        Image image = new Image(file.toURI().toString());
        System.out.println(image.getHeight());
        bigLogo.setImage(image);
    }
}
