package views.screen.home;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.dock.Dock;
import views.screen.FXMLScreenHandler;

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

    @FXML
    private AnchorPane dockPane;

    private HomeScreenHandler homeScreen;
    private Dock dock;

    public DockListItemHandler(String screenPath, HomeScreenHandler homeScreen, Dock dock) throws IOException {
        super(screenPath);
        this.homeScreen = homeScreen;
        this.dock = dock;
        this.setDockInfo();
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
        setImage(dockImg, dock.getImageURL());
    }

    @FXML
    void dockImgCLickListener(MouseEvent e) {
        homeScreen.onDockListItemClicked(dock);
    }
}
