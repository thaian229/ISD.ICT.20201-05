package views.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.BaseScreenHandlerWithBarcodePopup;
import views.screen.FXMLScreenHandler;
import views.screen.popup.BarcodePopup;

import java.io.IOException;

/**
 * class for ...
 *
 * @author mHoang
 * <p>
 * created_at: 25/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class NavBarHandler extends FXMLScreenHandler {
    @FXML
    ImageView logo;

    @FXML
    ImageView back;

    @FXML
    Button barcodeButton;

    private BaseScreenHandler parentScreenHandler;

    public NavBarHandler(BaseScreenHandler screenHandler, boolean buttonVisibility) throws IOException {
        super(Path.NAVBAR_PATH);
        this.parentScreenHandler = screenHandler;
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
        barcodeButton.setVisible(buttonVisibility);
    }

    @FXML
    void backClickHandler(MouseEvent e) {
            BaseScreenHandler previousScreen = parentScreenHandler.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
    }

    @FXML
    void logoClickHandler(MouseEvent e) {
            BaseScreenHandler homeScreen = parentScreenHandler.getHomeScreenHandler();
            homeScreen.setScreenTitle(homeScreen.getScreenTitle());
            homeScreen.show();
    }

    @FXML
    void barcodeButtonClickHandler(MouseEvent e) {
        if(parentScreenHandler instanceof BaseScreenHandlerWithBarcodePopup) {
            try {
                BarcodePopup.display((BaseScreenHandlerWithBarcodePopup) parentScreenHandler);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }


    }




}
