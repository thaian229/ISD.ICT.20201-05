package views.screen.splash;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenHandler implements Initializable {
    @FXML
    ImageView bigLogo;

    @FXML
    Button start;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("file:assets/images/BigLOGO.png");
        Image image = new Image(file.toURI().toString());
        System.out.println(image.getHeight());
        bigLogo.setImage(image);
    }
}
