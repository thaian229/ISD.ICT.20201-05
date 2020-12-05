package views.screen.home;

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
import utils.Utils;
import views.screen.BaseScreenHandler;
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
        this.setImage();

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

        displayDockList();
    }

    /**
     * set image for home screen
     */
    private void setImage() {
        // fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "LOGO.png");
        Image img1 = new Image(file1.toURI().toString());
        logo.setImage(img1);

        File file2 = new File(Configs.IMAGE_PATH + "/" + "backButton.png");
        Image img2 = new Image(file2.toURI().toString());
        back.setImage(img2);

        File file3 = new File(Configs.IMAGE_PATH + "/" + "search.png");
        Image img3 = new Image(file3.toURI().toString());
        searchImg.setImage(img3);
    }

    /**
     * @author mHoang
     * display list of docks in the home screen
     */
    private void displayDockList() {
        // clear all old cartMedia
        vboxDockList.getChildren().clear();

        // get list media of cart after check availability

        try {
//            dockList = ((HomeScreenController) this.getBController()).getDockList();
            dockList = (new HomeScreenController()).getDockList();

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


}
