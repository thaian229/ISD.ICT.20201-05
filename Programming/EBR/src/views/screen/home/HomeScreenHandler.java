package views.screen.home;

import controller.DockScreenController;
import controller.HomeScreenController;
import controller.SessionScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.session.Session;
import utils.Path;
import utils.Utils;
import views.component.NavBarHandler;
import views.screen.BaseScreenHandler;
import views.screen.BaseScreenHandlerWithBarcodePopup;
import views.screen.bike.BikeScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.popup.BarcodePopup;
import views.screen.session.SessionScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class HomeScreenHandler extends BaseScreenHandlerWithBarcodePopup implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private Pane navbar;

    @FXML
    private ImageView searchImg;

    @FXML
    private TextField searchField;

    @FXML
    private VBox vboxDockList1;

    @FXML
    private VBox vboxDockList2;

    private ArrayList<Dock> dockList;

    public HomeScreenHandler(Stage stage, String screenPath, HomeScreenController homeScreenController) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Home Screen";
        super.setBController(homeScreenController);
        dockList = this.getBController().getDockList();
        navbar.getChildren().add(new NavBarHandler(this, true).getContent());
    }

    @Override
    public HomeScreenController getBController() {
        return (HomeScreenController) super.getBController();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setImages();
    }

    /**
     * set image for home screen
     */
    private void setImages() {
        setImage(searchImg, Path.SEARCH_ICON);
    }

    /**
     * @author mHoang
     * display list of docks in the home screen
     */
    public void displayDockList() {
        vboxDockList1.getChildren().clear();
        vboxDockList2.getChildren().clear();
        try {
            for (Dock dock : dockList) {
                DockListItemHandler dockListItem = new DockListItemHandler(Path.DOCK_LIST_ITEM_PATH, this, dock);
                if (dockList.indexOf(dock) % 2 == 0)
                    vboxDockList1.getChildren().add(dockListItem.getContent());
                else vboxDockList2.getChildren().add(dockListItem.getContent());

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * move to dock that being clicked
     * @param dock {@link Dock} dock to be shown
     */
    public void onDockListItemClicked(Dock dock) {
        try {
            DockScreenHandler dockScreenHandler = new DockScreenHandler(this.stage, Path.DOCK_PATH, dock);
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


    @FXML
    void searchImgListener(MouseEvent e) {
        System.out.println("clicked");
        this.dockList = this.getBController().getDockListByKeyword(searchField.getText());
        displayDockList();
    }

    @Override
    public void show() {
        super.show();
        displayDockList();
    }
}
