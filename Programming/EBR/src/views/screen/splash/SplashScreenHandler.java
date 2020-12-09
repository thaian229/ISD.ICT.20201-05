package views.screen.splash;

import controller.home.HomeScreenController;
import controller.renting.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.BikeManager;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentScreenHandler;

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
        this.setImage();
        start.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                HomeScreenHandler homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
                homeScreenHandler.setBController(new HomeScreenController());
                homeScreenHandler.setScreenTitle(homeScreenHandler.getScreenTitle());
                homeScreenHandler.setPreviousScreen(this);
//                homeScreenHandler.show();
                Bike bike = BikeManager.getInstance().getBikeList().get(0);
                System.out.println(bike.getBarcode());
                PaymentScreenController paymentScreenController = new PaymentScreenController(bike);
                PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH);
                paymentScreenHandler.setBController(paymentScreenController);
                paymentScreenHandler.setHomeScreenHandler(homeScreenHandler);
                paymentScreenHandler.setPreviousScreen(this);
                paymentScreenHandler.setScreenTitle("Payment Screen");
                paymentScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * set image for the splash screen
     * @author mHoang
     */
    private void setImage() {
        File file = new File(Configs.IMAGE_PATH + "/BigLOGO.png");
        Image image = new Image(file.toURI().toString());
        System.out.println(image.getHeight());
        bigLogo.setImage(image);
    }
}
