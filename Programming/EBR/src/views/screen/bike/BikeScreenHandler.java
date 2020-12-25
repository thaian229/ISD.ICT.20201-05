package views.screen.bike;

import controller.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.bike.TwinElectricalBike;
import utils.Configs;
import utils.Path;
import views.component.NavBarHandler;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handler for dock list item
 *
 * @author Bui Tu Hoang
 * <p>
 * created_at: 1/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class BikeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Button barcodeButton;

    @FXML
    private Text bikeBarcode;

    @FXML
    private Text bikeDockName;

    @FXML
    private Text bikeBattery;

    @FXML
    private Text bikeUsage;

    @FXML
    private Text bikeDeposit;

    @FXML
    private Text bikeCharge;

    @FXML
    private Text saddleNum;

    @FXML
    private Text pedalsNum;

    @FXML
    private Text rearSeatNum;

    @FXML
    private ImageView bikeImage;
    @FXML
    private ImageView saddleImg;
    @FXML
    private ImageView pedalsImg;
    @FXML
    private ImageView rearSeatImg;
    @FXML
    private ImageView batteryImg;

    @FXML
    private Button rentNowButton;

    @FXML
    private Pane navbar;

    private Bike bike;

    /**
     * Constructor and initial setup for screen
     * @param stage {@link Stage}
     * @param screenPath path to fxml
     * @param bike {@link Bike} bike to be display
     * @throws IOException IO errors
     */
    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
        super.screenTitle = "Bike Screen";
        this.setImages();
        displayBike();
        navbar.getChildren().add(new NavBarHandler(this, false, true, true).getContent());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rentNowButton.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                PaymentScreenController paymentScreenController = new PaymentScreenController(bike);
                PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(this.stage, Path.PAYMENT_SCREEN_PATH, paymentScreenController);
                paymentScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreenHandler.setPreviousScreen(this);
                paymentScreenHandler.setScreenTitle(paymentScreenHandler.getScreenTitle());
                paymentScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }

    /**
     * Show bike info
     */
    private void displayBike() {
        try {
            bikeBarcode.setText(Integer.toString(bike.getBarcode()));
            bikeDeposit.setText(bike.getDeposit() + " " + Configs.CURRENCY);
            bikeCharge.setText(bike.getCharge() + " " + Configs.CURRENCY + "/h");
            bikeDockName.setText(bike.getDock().getName());
            pedalsNum.setText(bike.getPairOfPedals() + "");
            saddleNum.setText(bike.getSaddle() + "");
            rearSeatNum.setText(bike.getRearSeat() + "");
            if (bike instanceof StandardElectricalBike) {
                bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((StandardElectricalBike) bike).getTimeLeft() + " minutes");
            } else {
                bikeBattery.setVisible(false);
                bikeUsage.setVisible(false);
                batteryImg.setVisible(false);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setImages() {
        setImage(saddleImg, Path.SADDLE_ICON);
        setImage(pedalsImg, Path.PEDALS_ICON);
        setImage(rearSeatImg, Path.REAR_SEAT_ICON);
        setImage(batteryImg, Path.BATTERY_ICON);
        setImage(bikeImage, bike.getImageURL());
    }
}
