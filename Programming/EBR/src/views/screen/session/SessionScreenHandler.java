package views.screen.session;

import controller.ReturningDockSelectionController;
import controller.SessionScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.bike.TwinElectricalBike;
import model.session.Session;
import utils.Configs;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.returningDock.ReturningDockSelectionHandler;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Handler for the Session Screen
 *
 * @author thaian, khang
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

public class SessionScreenHandler extends BaseScreenHandler implements Initializable {

    private Session session;
    private SessionScreenController controller;

    @FXML
    ImageView logo;

    @FXML
    ImageView back;

    @FXML
    ImageView sessionBikeImage;

    @FXML
    Text sessionBarcode;

    @FXML
    Text sessionStartTime;

    @FXML
    Text sessionLength;

    @FXML
    Text sessionBattery;

    @FXML
    Text sessionUsage;

    @FXML
    Text sessionCharge;

    @FXML
    Text sessionRentingFee;

    @FXML
    Button sessionCloseButton;

    @FXML
    Button returnBikeButton;

    public SessionScreenHandler(Stage stage, String screenPath, Session session, SessionScreenController controller) throws IOException {
        super(stage, screenPath);
        this.session = session;
        System.out.println(session.getId());
        this.controller = controller;
        this.setImages();
        this.setTextFields();

        returnBikeButton.setOnMouseClicked(e -> {
            try {
                goToDockSelection();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> this.getPreviousScreen().show());
        sessionCloseButton.setOnMouseClicked(e -> homeScreenHandler.show());
    }

    private void setImages() {
        try {
            setImage(sessionBikeImage, this.session.getBike().getImageURL());
            setImage(logo, Path.LOGO_ICON);
            setImage(back, Path.BACK_NAV_ICON);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void setTextFields() {
        try {
            Bike bike = this.session.getBike();
            sessionBarcode.setText(Integer.toString(bike.getBarcode()));
            sessionStartTime.setText(this.session.getStartTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));

            if (this.session.getBike() instanceof StandardElectricalBike) {
                StandardElectricalBike eBike = (StandardElectricalBike) bike;
                sessionBattery.setText(Float.toString(eBike.getBattery()));
                sessionUsage.setText(Integer.toString(eBike.getTimeLeft()));
            } else if (this.session.getBike() instanceof TwinElectricalBike) {
                TwinElectricalBike eBike = (TwinElectricalBike) bike;
                sessionBattery.setText(Float.toString(eBike.getBattery()) + "%");
                sessionUsage.setText(Integer.toString(eBike.getTimeLeft()) + " minutes");
            } else {
                sessionBattery.setText("");
                sessionUsage.setText("");
            }
            sessionLength.setText(controller.calculateSessionLength(this.session) + " minutes");
            sessionCharge.setText(bike.getCharge() + " " + Configs.CURRENCY + "/h");
            sessionRentingFee.setText(controller.calculateCurrentRentingFees(this.session) + " VND");

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    private void goToDockSelection() throws IOException {
        try {
            ReturningDockSelectionHandler returningDockSelectionHandler = new ReturningDockSelectionHandler(this.stage, Path.RETURNING_DOCK_SELECTION_SCREEN_PATH, new ReturningDockSelectionController(), session);
            returningDockSelectionHandler.setPreviousScreen(this);
            returningDockSelectionHandler.setHomeScreenHandler(homeScreenHandler);
            returningDockSelectionHandler.setScreenTitle("Returning Dock Selection");
            returningDockSelectionHandler.show();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

    }
}
