package views.screen.session;

import controller.ReturningDockSelectionController;
import controller.SessionScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.bike.TwinElectricalBike;
import model.session.Session;
import utils.Configs;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.returningDock.ReturningDockSelectionHandler;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    private LocalTime realTime;

    @FXML
    ImageView logo;

    @FXML
    ImageView back;

    @FXML
    ImageView lockBikeImg;

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
        this.setBController(controller);
        this.setImages();
        this.realTime = LocalTime.ofSecondOfDay(session.getSessionLength());
        this.setTextFields();
        System.out.println(session.isActive());
        setImageForLockBikeImg();


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if(session.isActive()) {
                updateCurrentTime();
                sessionLength.setText(this.realTime.toString());
                sessionRentingFee.setText(this.getBController().calculateCurrentRentingFees(this.session) + " VND");
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        returnBikeButton.setOnMouseClicked(e -> {
            try {
                if(this.session.isActive()) {
                    this.getBController().changeBikeLockState(session);
                }
                goToDockSelection();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });

        lockBikeImg.setOnMouseClicked(e -> {
            lockBikeImg.setDisable(true);
            this.getBController().changeBikeLockState(session);
            setImageForLockBikeImg();
            lockBikeImg.setDisable(false);
        });
    }

    private void updateCurrentTime() {
        this.realTime = LocalTime.ofSecondOfDay(this.realTime.toSecondOfDay() + 1);
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
            setImageForLockBikeImg();
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
                sessionBattery.setText(eBike.getBattery() + "%");
                sessionUsage.setText(eBike.getTimeLeft() + " minutes");
            } else {
                sessionBattery.setText("");
                sessionUsage.setText("");
            }
            sessionLength.setText(this.realTime.toString());
            sessionCharge.setText(bike.getCharge() + " " + Configs.CURRENCY + "/h");
            sessionRentingFee.setText(this.getBController().calculateCurrentRentingFees(this.session) + " VND");

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

    @Override
    public SessionScreenController getBController() {
        return (SessionScreenController) super.getBController();
    }

    private void setImageForLockBikeImg() {
        if (!session.isActive()) {
            setImage(lockBikeImg, Path.PLAY_CIRCLE_ICON);
        } else {
            setImage(lockBikeImg, Path.PAUSE_CIRCLE_ICON);
        }
    }
//    @FXML
//    void lockBikeImgClickHandler (MouseEvent e) {
//        this.getBController().changeBikeLockState(session);
//        if (!session.isActive()) {
//            setImage(lockBikeImg, Path.PLAY_CIRCLE_ICON);
//        } else {
//            setImage(lockBikeImg, Path.PAUSE_CIRCLE_ICON);
//        }
//    }
}
