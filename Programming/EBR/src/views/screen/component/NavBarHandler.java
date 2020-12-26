package views.screen.component;

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

    /**
     * Constructor for the nav bar
     * @param screenHandler responsible handler
     * @param buttonVisibility true/false to show/hide optional button
     * @param backVisibility show/hide back button
     * @param homeEnable show/hide home button
     * @throws IOException IO errors
     */
    public NavBarHandler(BaseScreenHandler screenHandler, boolean buttonVisibility,  boolean backVisibility,  boolean homeEnable) throws IOException {
        super(Path.NAVBAR_PATH);
        this.parentScreenHandler = screenHandler;
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
        barcodeButton.setVisible(buttonVisibility);
        barcodeButton.setDisable(!buttonVisibility);
        logo.setDisable(!homeEnable);
        back.setVisible(backVisibility);
        back.setDisable(!backVisibility);
    }

    /**
     * go to previous screen
     * @param e {@link MouseEvent}
     */
    @FXML
    void backClickHandler(MouseEvent e) {
            BaseScreenHandler previousScreen = parentScreenHandler.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
    }

    /**
     * go to home screen
     * @param e {@link MouseEvent}
     */
    @FXML
    void logoClickHandler(MouseEvent e) {
        try {
            BaseScreenHandler homeScreen = parentScreenHandler.getHomeScreenHandler();
            homeScreen.setScreenTitle(homeScreen.getScreenTitle());
            homeScreen.show();
        } catch (NullPointerException ex) {
        }
    }

    /**
     * bring up barcode popup
     * @param e {@link MouseEvent}
     */
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
