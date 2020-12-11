package views.screen.popup;

import controller.renting.SessionScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bike.Bike;
import model.bike.BikeManager;
import model.session.Session;
import model.session.SessionManager;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.session.SessionScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * class for ...
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

public class PopupScreen extends BaseScreenHandler implements Initializable {


    @FXML
    ImageView logo;

    @FXML
    TextField barcodeInput;

    @FXML
    Button continueBtn;

    public PopupScreen(Stage stage) throws IOException {
        super(stage, Configs.POPUP_PATH);
    }

    private static PopupScreen popup() throws IOException {
        PopupScreen popup = new PopupScreen(new Stage());
        popup.stage.initStyle(StageStyle.DECORATED);
        return popup;
    }

    public static void display() throws IOException {
        popup().show();
    }

    private void setImage() {
        File file = new File(Configs.IMAGE_PATH + "/LOGO_R.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImage();
        continueBtn.setOnMouseClicked(e -> {
            handleBarcodeEnter();
        });
    }

    private void handleBarcodeEnter() {
        // Take barcode
        int barcode = Integer.parseInt(barcodeInput.getText().trim());
        Bike bike = BikeManager.getInstance().getBikeByBarcode(barcode);
        if (bike == null) {
            barcodeInput.setText("");
            return;
        }

        // Check bike is rented or not
        ArrayList<Session> sessions = SessionManager.getInstance().getSessions();
        Session rentedSession = null;
        boolean isRented = false;
        for (Session session : sessions) {
            if (session.getEndTime() == null) {
                if (session.getBike().getBarcode() == barcode) {
                    isRented = true;
                    rentedSession = session;
                }
            }
        }

        // Move to corresponding screen
        if (isRented) { // rented : to Session View
            this.moveToSessionScreen(rentedSession);
        } else {    // not rent yet : to Bike View Screen
            this.moveToBikeViewScreen(bike);
        }
    }

    private void moveToSessionScreen(Session session) {
        SessionScreenController sessionScreenController = new SessionScreenController();
        try {
            SessionScreenHandler sessionScreenHandler = new SessionScreenHandler(this.stage,
                    Configs.SESSION_SCREEN_PATH, session, sessionScreenController);

            sessionScreenHandler.setHomeScreenHandler(homeScreenHandler);
            sessionScreenHandler.setPreviousScreen(this.getPreviousScreen());
            sessionScreenHandler.setScreenTitle("Session Screen");
            sessionScreenHandler.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void moveToBikeViewScreen(Bike bike) {
        try {
            BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(this.stage,
                    Configs.BIKE_VIEW_SCREEN_PATH, bike);

            bikeScreenHandler.setHomeScreenHandler(homeScreenHandler);
            bikeScreenHandler.setPreviousScreen(this.getPreviousScreen());
            bikeScreenHandler.setScreenTitle("Bike View Screen");
            bikeScreenHandler.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
