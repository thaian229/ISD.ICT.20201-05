package views.screen.dock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.bike.*;
import utils.Path;
import views.screen.FXMLScreenHandler;

import java.io.IOException;

/**
 * Handler to display bike in the bike list of dock screen
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
public class BikeListItemHandler extends FXMLScreenHandler {
    @FXML
    private Text bikeType;

    @FXML
    private Text bikeCode;

    @FXML
    private Button rentButton;

    @FXML
    private Text bikeBattery;

    @FXML
    private ImageView bikeImg;

    @FXML
    private ImageView bikeIcon;

    @FXML
    private ImageView batteryIcon;


    private DockScreenHandler dockScreenHandler;

    private Bike bike;

    public BikeListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    /**
     * Constructor and initial setup for screen
     * @param screenPath path to fxml file
     * @param dockScreenHandler {@link DockScreenHandler}
     * @throws IOException IO errors
     */
    public BikeListItemHandler(String screenPath, DockScreenHandler dockScreenHandler) throws IOException {
        super(screenPath);
        this.dockScreenHandler = dockScreenHandler;
        rentButton.setOnMouseClicked(e -> {
            this.dockScreenHandler.BikeScreenTransition(bike);
        });
    }

    /**
     * set all images
     */
    private void setImages() {
        setImage(batteryIcon, Path.BATTERY_ICON);
        System.out.println();
        if (bike instanceof StandardBike) {
            setImage(bikeIcon, Path.STANDARD_BIKE_ICON);
        } else if (bike instanceof TwinBike) {
            setImage(bikeIcon, Path.TWIN_BIKE_ICON);
        } else if (bike instanceof StandardElectricalBike) {
            setImage(bikeIcon, Path.STANDARD_ELECTRICAL_BIKE_ICON);
        } else if (bike instanceof TwinElectricalBike) {
            setImage(bikeIcon, Path.TWIN_ELECTRICAL_BIKE_ICON);
        } else {
            setImage(bikeIcon, Path.STANDARD_BIKE_ICON);
        }
    }

    private void setBikeType() {
        System.out.println();
        if (bike instanceof StandardBike) {
            bikeType.setText("Standard Bike");
        } else if (bike instanceof TwinBike) {
            bikeType.setText("Twin Bike");
        } else if (bike instanceof StandardElectricalBike) {
            bikeType.setText("E-Bike");
        } else if (bike instanceof TwinElectricalBike) {
            bikeType.setText("Twin E-Bike");
        } else {
            bikeType.setText("NO DATA");
        }
    }

    public void setBike(Bike bike) {
        this.bike = bike;
        this.setBikeType();
        this.setBikeInfo();
        this.setImages();
    }

    /**
     * update view of bike info
     */
    private void setBikeInfo() {
        bikeCode.setText("" + bike.getBarcode());
        if (bike instanceof StandardElectricalBike) {
            bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
        } else if (bike instanceof TwinElectricalBike) {
            bikeBattery.setText(((TwinElectricalBike) bike).getBattery() + "%");
        } else {
            bikeBattery.setVisible(false);
            batteryIcon.setVisible(false);
        }
        setImage(bikeImg, bike.getImageURL());
    }

}
