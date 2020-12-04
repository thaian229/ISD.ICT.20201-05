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

public class DockListItemHandler extends FXMLScreenHandler {

    private HomeScreenHandler homeScreen;
    private Dock dock;

    public DockListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    public DockListItemHandler(String screenPath, HomeScreenHandler homeScreen) throws IOException {
        super(screenPath);
        this.homeScreen = homeScreen;
//        hboxMedia.setAlignment(Pos.CENTER);
    }


    public void setDock(Dock dock) {
        this.dock = dock;
        this.setDockInfo();
    }

    private void setDockInfo() {

    }
}
