package views.screen.dock;

import controller.DockScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.*;
import model.dock.Dock;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Handler for Dock View Screen
 *
 * @author mHoang
 * <p>
 * created_at: 6/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
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
    private ImageView dockImg;

    @FXML
    private Text dockName;

    @FXML
    private Text dockAddress;

    @FXML
    private ImageView parkingIcon;

    @FXML
    private ImageView standardBikeIcon;

    @FXML
    private ImageView twinBikeIcon;

    @FXML
    private ImageView standardEBikeIcon;

    @FXML
    private ImageView twinEBikeIcon;

    @FXML
    private Text dockCapacity;

    @FXML
    private Text dockStandardBikeNum;

    @FXML
    private Text dockTwinBikeNum;

    @FXML
    private Text dockEBikeNum;

    @FXML
    private Text dockTwinEBikeNum;


    @FXML
    private HBox hboxBikeList;

    private final Dock dock;

    private ArrayList<Bike> bikeList;


    public DockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
        super(stage, screenPath);
        this.dock = dock;
        super.screenTitle = "Dock Screen";
        this.setImages();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> {
            BaseScreenHandler previousScreen = this.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
        });
    }

    /**
     * Show all bikes in dock
     */
    public void displayBikeList() {
        hboxBikeList.getChildren().clear();

        try {
            this.bikeList = ((DockScreenController) this.getBController()).getBikeListOfDock(dock.getId());

            for (Bike bike : bikeList) {

                BikeListItemHandler bikeListItem = new BikeListItemHandler(Path.BIKE_LIST_ITEM_PATH, this);

                bikeListItem.setBike(bike);

                hboxBikeList.getChildren().add(bikeListItem.getContent());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        dock.clearBikeList();
        dock.addListOfBikes(this.bikeList);
        setDockDetail();
    }

    private void setImages() {
        setImage(parkingIcon, Path.PARKING_ICON);
        setImage(standardBikeIcon, Path.STANDARD_BIKE_ICON);
        setImage(twinBikeIcon, Path.TWIN_BIKE_ICON);
        setImage(standardEBikeIcon, Path.STANDARD_BIKE_ICON);
        setImage(twinEBikeIcon, Path.TWIN_ELECTRICAL_BIKE_ICON);
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
        setImage(dockImg, dock.getImageURL());
    }

    /**
     * set all label and text fields value
     */
    private void setDockDetail() {
        dockName.setText(dock.getName());
        dockAddress.setText(dock.getLocation());
        setImage(dockImg, dock.getImageURL());
        dockCapacity.setText("" + dock.getCapacity());

        int sb = 0;
        int tb = 0;
        int eb = 0;
        int teb = 0;
        for (Bike bike : dock.getBikeList()) {
            if (bike instanceof TwinBike) {
                tb += 1;
            } else if (bike instanceof StandardBike) {
                sb += 1;
            } else if (bike instanceof TwinElectricalBike) {
                teb += 1;
            } else if (bike instanceof StandardElectricalBike) {
                eb += 1;
            }
        }

        dockStandardBikeNum.setText("" + sb);
        dockTwinBikeNum.setText("" + tb);
        dockEBikeNum.setText("" + eb);
        dockTwinEBikeNum.setText("" + teb);
    }

    /**
     * move to bike screen
     * @param bike {@link Bike} bike to be shown
     */
    public void BikeScreenTransition(Bike bike) {
        try {
            bike.setDock(dock);
            BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(this.stage, Path.BIKE_PATH, bike);
            bikeScreenHandler.setScreenTitle(bikeScreenHandler.getScreenTitle());
            bikeScreenHandler.setPreviousScreen(this);
            bikeScreenHandler.setHomeScreenHandler(homeScreenHandler);
            bikeScreenHandler.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
