package views.screen.home;

import controller.DockScreenController;
import controller.home.HomeScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dock.Dock;
import utils.Configs;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Handler for home screen
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

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private ImageView logo;

    @FXML
    private ImageView back;

    @FXML
    private ImageView searchImg;

    @FXML
    private Button barcodeButton;

    @FXML
    private VBox vboxDockList;

    private ArrayList<Dock> dockList;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Home Screen";
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setImages();

        back.setOnMouseClicked(e -> {
            BaseScreenHandler previousScreen = this.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
        });

        barcodeButton.setOnMouseClicked(e -> {
            try {
                PopupScreen.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    /**
     * set image for home screen
     */
    private void setImages() {
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
        setImage(searchImg, Path.SEARCH_ICON);
    }

    /**
     * @author mHoang
     * display list of docks in the home screen
     */
    public void displayDockList() {
        vboxDockList.getChildren().clear();

        try {
            dockList = ((HomeScreenController) this.getBController()).getDockList();
//            dockList = (new HomeScreenController()).getDockList();

            for (Dock dock : dockList) {

                // display the attribute of vboxCart media
                DockListItemHandler dockListItem = new DockListItemHandler(Configs.DOCK_LIST_ITEM_PATH, this);
                dockListItem.setDock(dock);

                // add spinner
                vboxDockList.getChildren().add(dockListItem.getContent());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onDockListItemClicked(Dock dock) {
        try {
            DockScreenHandler dockScreenHandler = new DockScreenHandler(this.stage, Configs.DOCK_PATH, dock);
            dockScreenHandler.setBController(new DockScreenController());
            dockScreenHandler.displayBikeList();
            dockScreenHandler.setScreenTitle(dockScreenHandler.getScreenTitle());
            dockScreenHandler.setHomeScreenHandler(this);
            dockScreenHandler.setPreviousScreen(this);
            dockScreenHandler.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
