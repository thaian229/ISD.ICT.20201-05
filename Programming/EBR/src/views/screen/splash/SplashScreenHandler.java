package views.screen.splash;

import controller.home.HomeScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handler for the Splash Screen
 *
 * @author mHoang
 * <p>
 * created_at: 4/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */

public class SplashScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    ImageView bigLogo;

    @FXML
    Button start;

    public SplashScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Splash Screen";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage(bigLogo, Path.BIG_LOGO_ICON);
        start.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                HomeScreenHandler homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
                homeScreenHandler.setBController(new HomeScreenController());
                homeScreenHandler.displayDockList();
                homeScreenHandler.setScreenTitle(homeScreenHandler.getScreenTitle());
                homeScreenHandler.setPreviousScreen(this);
                homeScreenHandler.show();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
