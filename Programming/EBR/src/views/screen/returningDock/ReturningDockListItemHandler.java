package views.screen.returningDock;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.dock.Dock;
import views.screen.FXMLScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;

public class ReturningDockListItemHandler extends FXMLScreenHandler {
    @FXML
    private Text dockName;

    @FXML
    private Text dockParkingSlots;

    @FXML
    private ImageView dockImg;

    private ReturningDockSelectionHandler returningDockSelectionHandler;
    private Dock dock;

    public ReturningDockListItemHandler(String screenPath, ReturningDockSelectionHandler returningDockSelectionHandler) throws IOException {
        super(screenPath);
        this.returningDockSelectionHandler = returningDockSelectionHandler;
        dockImg.setOnMouseClicked(e -> {
            returningDockSelectionHandler.onDockListItemClicked(dock);
        });
    }

    public ReturningDockListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }


    /**
     * set the dock for handler and its info
     *
     * @param dock dock's instance
     * @author mHoang
     */
    public void setDock(Dock dock) {
        this.dock = dock;
        this.setDockInfo();
    }

    /**
     * set elements of the dock list item
     *
     * @author mHoang
     */
    private void setDockInfo() {
        dockName.setText(dock.getName());
        dockParkingSlots.setText(Integer.toString(dock.getNumberOfAvailableBike()) + '/' + Integer.toString(dock.getCapacity()));
        setImage(dockImg, dock.getImageURL());
    }
}
