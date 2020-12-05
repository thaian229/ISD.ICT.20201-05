package views.screen.home;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.dock.Dock;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Handler for dock list item
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

public class DockListItemHandler extends FXMLScreenHandler {

    private HomeScreenHandler homeScreen;
    private Dock dock;

    public DockListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    public DockListItemHandler(String screenPath, HomeScreenHandler homeScreen) throws IOException {
        super(screenPath);
        this.homeScreen = homeScreen;
    }


    /**
     * set the dock for handler and its info
     *
     * @param dock
     * @author mHoang
     */
    public void setDock(Dock dock) {
        this.dock = dock;
        this.setDockInfo();
    }

    /**
     * set elements of the dock list item
     *
     * @author mHoang
     */
    private void setDockInfo() {

    }
}
