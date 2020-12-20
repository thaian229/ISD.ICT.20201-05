package views.screen.dock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.bike.*;
import utils.Path;
import views.screen.FXMLScreenHandler;

import java.io.File;
import java.io.IOException;

public class BikeListItemHandler extends FXMLScreenHandler {
    @FXML
    private Text bikeName;

    @FXML
    private Text bikeSaddle;

    @FXML
    private Text bikePedal;

    @FXML
    private Text bikeRearSeat;

    @FXML
    private Button rentButton;

    @FXML
    private Text bikeBattery;

    @FXML
    private Text bikeUsageTime;

    @FXML
    private GridPane bikeElecInfo;

    @FXML
    private ImageView bikeImg;

    @FXML
    private ImageView bikeIcon;

    @FXML
    private ImageView batteryIcon;

    @FXML
    private ImageView clockIcon;



    private DockScreenHandler dockScreenHandler;

    private Bike bike;

    public BikeListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    public BikeListItemHandler(String screenPath, DockScreenHandler dockScreenHandler) throws IOException {
        super(screenPath);
        this.dockScreenHandler = dockScreenHandler;
        rentButton.setOnMouseClicked(e -> {
            this.dockScreenHandler.BikeScreenTransition(bike);
        });
    }

    private void setImage() {
        setImage(clockIcon, Path.CLOCK_ICON);
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

    public void setBike(Bike bike) {
        this.bike = bike;
        this.setBikeInfo();
        this.setImage();
    }

    private void setBikeInfo() {
        bikeName.setText("" + bike.getBarcode());
        bikePedal.setText("" + bike.getPairOfPedals());
        bikeSaddle.setText("" + bike.getSaddle());
        bikeRearSeat.setText("" + bike.getRearSeat());
        if (bike instanceof StandardElectricalBike) {
            bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
            bikeUsageTime.setText(((StandardElectricalBike) bike).getTimeLeft() + " seconds");
        } else if (bike instanceof TwinElectricalBike) {
            bikeBattery.setText(((TwinElectricalBike) bike).getBattery() + "%");
            bikeUsageTime.setText(((TwinElectricalBike) bike).getTimeLeft() + " seconds");
        } else {
            bikeElecInfo.setVisible(false);
        }
        this.setBikeImage();
    }

    private void setBikeImage() {
        File file = new File(bike.getImageURL());
        Image img = new Image(file.toURI().toString());
        bikeImg.setImage(img);
    }

}
