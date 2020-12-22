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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.session.Session;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.dock.DockScreenHandler;
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

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private ImageView logo;

    @FXML
    private ImageView back;

    @FXML
    private ImageView searchImg;

    @FXML
    private TextField searchField;

    @FXML
    private Button barcodeButton;

    @FXML
    private VBox vboxDockList;

    private ArrayList<Dock> dockList;

    public HomeScreenHandler(Stage stage, String screenPath, HomeScreenController homeScreenController) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Home Screen";
        super.setBController(homeScreenController);
        dockList = this.getBController().getDockList();
    }

    @Override
    public HomeScreenController getBController() {
        return (HomeScreenController) super.getBController();
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
                BarcodePopup.display(this);
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
            for (Dock dock : dockList) {
                DockListItemHandler dockListItem = new DockListItemHandler(Path.DOCK_LIST_ITEM_PATH, this, dock);
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


    public void moveToSessionScreen(Session session) {
        SessionScreenController sessionScreenController = new SessionScreenController();
        try {
            SessionScreenHandler sessionScreenHandler = new SessionScreenHandler(this.stage,
                    Path.SESSION_SCREEN_PATH, session, sessionScreenController);
            sessionScreenHandler.setHomeScreenHandler(this);
            sessionScreenHandler.setPreviousScreen(this);
            sessionScreenHandler.setScreenTitle("Session Screen");
            sessionScreenHandler.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void moveToBikeViewScreen(Bike bike) {
        try {
            BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(this.stage,
                    Path.BIKE_VIEW_SCREEN_PATH, bike);
            bikeScreenHandler.setHomeScreenHandler(this);
            bikeScreenHandler.setPreviousScreen(this);
            bikeScreenHandler.setScreenTitle("Bike View Screen");
            bikeScreenHandler.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    void searchImgListener(MouseEvent e) {
        System.out.println("clicked");
        this.dockList = this.getBController().getDockListByKeyword(searchField.getText());
        displayDockList();
    }
}
