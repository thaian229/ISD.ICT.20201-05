package views.screen.dock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.dock.Dock;
import views.screen.FXMLScreenHandler;

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

    private DockScreenHandler dockScreenHandler;

    private Bike bike;

    public BikeListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    public BikeListItemHandler(String screenPath, DockScreenHandler dockScreenHandler) throws IOException {
        super(screenPath);
        this.dockScreenHandler = dockScreenHandler;
        this.setImage();
    }

    private void setImage() {

    }

    public void setBike(Bike bike) {
        this.bike = bike;
        this.setBikeInfo();
    }

    private void setBikeInfo() {
        bikeName.setText("" + bike.getBarcode());
        bikePedal.setText("" + bike.getPairOfPedals());
        bikeSaddle.setText("" + bike.getSaddle());
        bikeRearSeat.setText("" + bike.getRearSeat());
        if(bike instanceof StandardElectricalBike) {

        } else {
            bikeElecInfo.setOpacity(0);
        }
    }

}
