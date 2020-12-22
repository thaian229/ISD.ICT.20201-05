package views.screen.home;

import controller.SessionScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bike.Bike;
import model.bike.BikeManager;
import model.dock.DockManager;
import model.session.Session;
import model.session.SessionManager;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.session.SessionScreenHandler;

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

public class BarcodePopup extends BaseScreenHandler implements Initializable {


    @FXML
    ImageView logo;

    @FXML
    TextField barcodeInput;

    @FXML
    Button continueBtn;

    HomeScreenHandler homeScreenHandler;

    public BarcodePopup(Stage stage, HomeScreenHandler homeScreenHandler) throws IOException {
        super(stage, Path.POPUP_PATH);
        this.homeScreenHandler = homeScreenHandler;
    }

    private static BarcodePopup popup(HomeScreenHandler homeScreenHandler) throws IOException {
        BarcodePopup popup = new BarcodePopup(new Stage(), homeScreenHandler);
        popup.stage.initStyle(StageStyle.DECORATED);
        return popup;
    }

    public static void display(HomeScreenHandler homeScreenHandler) throws IOException {
        popup(homeScreenHandler).show();
    }

    private void setImages() {
        setImage(logo, Path.LOGO_R_ICON);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImages();
        continueBtn.setOnMouseClicked(e -> {
            handleBarcodeEnter();
        });
    }

    private void handleBarcodeEnter() {
        // Take barcode
        int barcode = Integer.parseInt(barcodeInput.getText().trim());
        Bike bike = BikeManager.getInstance().getBikeByBarcode(barcode);
        bike.setDock(DockManager.getInstance().getDockById(bike.getDockId()));
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
        {
            if (isRented) { // rented : to Session View
                homeScreenHandler.moveToSessionScreen(rentedSession);
            } else {    // not rent yet : to Bike View Screen
                homeScreenHandler.moveToBikeViewScreen(bike);
            }
            this.stage.close();
        }
    }

}
