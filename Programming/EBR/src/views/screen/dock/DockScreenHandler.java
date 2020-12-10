package views.screen.dock;

import controller.DockScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.*;
import model.dock.Dock;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
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
    private VBox vboxBikeList;

    private final Dock dock;

    private ArrayList<Bike> bikeList;


    public DockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
        super(stage, screenPath);
        this.dock = dock;
        super.screenTitle = "Dock Screen";
        this.setImage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            this.bikeList = ((DockScreenController) this.getBController()).getBikeListOfDock(dock.getId());

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

        dock.getBikeList().clear();
        dock.getBikeList().addAll(this.bikeList);
        setDockDetail();
    }

    private void drawImageView(ImageView imageView, String imgPath) {
        File file = new File(imgPath);
        Image img = new Image(file.toURI().toString());
        imageView.setImage(img);
    }

    private void setImage() {
        drawImageView(parkingIcon, Path.PARKING_ICON);
        drawImageView(standardBikeIcon, Path.STANDARD_BIKE_ICON);
        drawImageView(twinBikeIcon, Path.TWIN_BIKE_ICON);
        drawImageView(standardEBikeIcon, Path.STANDARD_BIKE_ICON);
        drawImageView(twinEBikeIcon, Path.TWIN_ELECTRICAL_BIKE_ICON);
    }

    private void setDockDetail() {
        dockName.setText(dock.getName());
        dockAddress.setText(dock.getLocation());
        drawImageView(dockImg, dock.getImageURL());
        dockCapacity.setText("" + dock.getCapacity());

        int sb = 0;
        int tb = 0;
        int eb = 0;
        int teb = 0;
        for(Bike bike : dock.getBikeList()) {
            if(bike instanceof StandardBike) {
                sb += 1;
            } else if (bike instanceof TwinBike) {
                tb += 1;
            } else if (bike instanceof StandardElectricalBike) {
                eb += 1;
            } else if (bike instanceof TwinElectricalBike) {
                teb += 1;
            }
        }

        dockStandardBikeNum.setText("" + sb);
        dockTwinBikeNum.setText("" + tb);
        dockEBikeNum.setText("" + eb);
        dockTwinEBikeNum.setText("" + teb);
    }

    public void BikeScreenTransition(Bike bike) {
        try {
            bike.setDock(dock);
            BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(this.stage, Configs.BIKE_PATH, bike);
//            bikeScreenHandler.setBController(new ());
            bikeScreenHandler.setScreenTitle(bikeScreenHandler.getScreenTitle());
            bikeScreenHandler.setPreviousScreen(this);
            bikeScreenHandler.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
