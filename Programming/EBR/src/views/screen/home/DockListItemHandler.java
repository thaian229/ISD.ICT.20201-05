package views.screen.home;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.dock.Dock;
import utils.Configs;
import views.screen.FXMLScreenHandler;

import java.io.File;
import java.io.IOException;

/**
 * Handler for dock list item
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

public class DockListItemHandler extends FXMLScreenHandler {

    @FXML
    private Text dockName;

    @FXML
    private Text dockAddress;

    @FXML
    private Text dockBikeNum;

    @FXML
    private ImageView dockImg;

    private HomeScreenHandler homeScreen;
    private Dock dock;

    public DockListItemHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    public DockListItemHandler(String screenPath, HomeScreenHandler homeScreen) throws IOException {
        super(screenPath);
        this.homeScreen = homeScreen;
        dockImg.setOnMouseClicked(e -> {
            homeScreen.onDockListItemClicked(dock);
        });
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
        dockAddress.setText(dock.getLocation());
        dockBikeNum.setText(dock.getNumberOfAvailableBike() + "/" + dock.getCapacity());
        setImage();
    }

    private void setImage() {
        // fix image path caused by fxml
        File file = new File(dock.getImageURL());
        Image img = new Image(file.toURI().toString());
        dockImg.setImage(img);
    }
}
