package views.screen.session;

import controller.ReturningDockSelectionController;
import controller.SessionScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.session.Session;
import utils.Configs;
import utils.Path;
import utils.Utils;
import views.screen.component.NavBarHandler;
import views.screen.BaseScreenHandler;
import views.screen.returningDock.ReturningDockSelectionHandler;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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
    Text usageLabel;

    @FXML
    Text batteryLabel;

    @FXML
    Text sessionCharge;

    @FXML
    Text sessionRentingFee;

    @FXML
    Button sessionCloseButton;

    @FXML
    Button returnBikeButton;

    @FXML
    private Pane navbar;

    private Session session;

    private LocalTime realTime;

    private int days = 0;

    private int timePassed = 0;

    /**
     * Constructor and setup the screen
     * @param stage {@link Stage stage}
     * @param screenPath path to .fxml
     * @param session {@link Session session} session the screen works on
     * @param controller {@link SessionScreenController sessionScreenController} Controller of this screen
     * @throws IOException IO error
     */
    public SessionScreenHandler(Stage stage, String screenPath, Session session, SessionScreenController controller) throws IOException {
        super(stage, screenPath);
        this.session = session;
        System.out.println(session.getId());
        super.screenTitle = "Session Screen";
        this.setBController(controller);
        this.setImages();
        this.toDayAndTime(session.getSessionLength());
        setSessionLengthText();
        this.setTextFields();
        System.out.println(session.isActive());
        setImageForLockBikeImg();
        navbar.getChildren().add(new NavBarHandler(this, false, true, true).getContent());


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (session.isActive()) {
                toDayAndTime(session.getSessionLength());
                setSessionLengthText();
                sessionRentingFee.setText(this.getBController().calculateCurrentRentingFees(this.session) + " " + Configs.CURRENCY);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        returnBikeButton.setOnMouseClicked(e -> {
            try {
                if (this.session.isActive()) {
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

    /**
     * Set up all text field for displaying
     */
    private void setSessionLengthText() {
        if (days == 0) {
            sessionLength.setText(this.realTime.toString());
        } else {
            sessionLength.setText(days + " days, " + this.realTime.toString());
        }
        sessionRentingFee.setText(this.getBController().calculateCurrentRentingFees(this.session) + " VND");
    }

    /**
     * transform second to days and time
     * @param seconds number of seconds
     */
    private void toDayAndTime(long seconds) {
        this.days = (int) (seconds / 86399);
        this.realTime = LocalTime.ofSecondOfDay((seconds - days * 86399));
    }

    /**
     *
     * @param url {@link URL URL}
     * @param resourceBundle {@link ResourceBundle ResourceBundle}
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionCloseButton.setOnMouseClicked(e -> homeScreenHandler.show());
    }

    /**
     * set image of bike on session screen
     */
    private void setImages() {
        try {
            setImage(sessionBikeImage, this.session.getBike().getImageURL());
            setImageForLockBikeImg();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * update text fields value
     */
    private void setTextFields() {
        try {
            Bike bike = this.session.getBike();
            sessionBarcode.setText(Integer.toString(bike.getBarcode()));
            sessionStartTime.setText(this.session.getStartTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));

            if (this.session.getBike() instanceof StandardElectricalBike) {
                StandardElectricalBike eBike = (StandardElectricalBike) bike;
                sessionBattery.setText(eBike.getBattery() + "%");
                sessionUsage.setText(eBike.getTimeLeft() + " minutes");
            } else {
                sessionBattery.setVisible(false);
                sessionUsage.setVisible(false);
                batteryLabel.setVisible(false);
                usageLabel.setVisible(false);
            }

            sessionCharge.setText(bike.getCharge() + " " + Configs.CURRENCY + "/h");

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Go to dock screen
     * @throws IOException IO error
     */
    private void goToDockSelection() throws IOException {
        try {
            ReturningDockSelectionHandler returningDockSelectionHandler = new ReturningDockSelectionHandler(this.stage, Path.RETURNING_DOCK_SELECTION_SCREEN_PATH, new ReturningDockSelectionController(), session);
            returningDockSelectionHandler.setPreviousScreen(this);
            returningDockSelectionHandler.setHomeScreenHandler(homeScreenHandler);
            returningDockSelectionHandler.setScreenTitle(returningDockSelectionHandler.getScreenTitle());
            returningDockSelectionHandler.show();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

    }

    /**
     *
     * @return {@link SessionScreenController SessionScreenController}
     */
    @Override
    public SessionScreenController getBController() {
        return (SessionScreenController) super.getBController();
    }

    /**
     * set image for lock button
     */
    private void setImageForLockBikeImg() {
        if (!session.isActive()) {
            setImage(lockBikeImg, Path.PLAY_CIRCLE_ICON);
        } else {
            setImage(lockBikeImg, Path.PAUSE_CIRCLE_ICON);
        }
    }

}
