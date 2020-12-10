package views.screen.dock;

import controller.DockScreenController;
import controller.home.HomeScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.StandardBike;
import model.bike.StandardElectricalBike;
import model.dock.Dock;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.DockListItemHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DockScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private ImageView back;

    @FXML
    private ImageView searchImg;

    @FXML
    private Button barcodeButton;

    @FXML
    private VBox vboxBikeList;

    private final Dock dock;

    private ArrayList<Bike> bikeList;


    public DockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
        super(stage, screenPath);
        this.dock = dock;
        super.screenTitle = "Dock Screen";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    }

    public void displayBikeList() {
        vboxBikeList.getChildren().clear();

        try {
            bikeList = ((DockScreenController) this.getBController()).getBikeList();

            for (Bike bike : bikeList) {

                // display the attribute of vboxCart media
                BikeListItemHandler bikeListItem = new BikeListItemHandler(Configs.BIKE_LIST_ITEM_PATH, this);

                bikeListItem.setBike(bike);

                // add spinner
                vboxBikeList.getChildren().add(bikeListItem.getContent());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setImage() {

    }
}
