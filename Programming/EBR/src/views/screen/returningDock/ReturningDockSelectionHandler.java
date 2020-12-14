package views.screen.returningDock;

import controller.BaseController;
import controller.ReturningDockSelectionController;
import controller.home.HomeScreenController;
import controller.returning.InvoiceScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.BikeManager;
import model.dock.Dock;
import javafx.scene.control.Button;
import model.dock.DockManager;
import model.invoice.Invoice;
import model.session.Session;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.home.DockListItemHandler;
import views.screen.invoice.InvoiceScreenHandler;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturningDockSelectionHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private ImageView dockImg;
    @FXML
    private Text dockAddress;
    @FXML
    private Text dockEmptySlots;
    @FXML
    private TextField searchField;
    @FXML
    private Button returnBikeBtn;
    @FXML
    private VBox vboxDockList;
    @FXML
    private AnchorPane dockInfo;
    @FXML
    private ImageView searchImg;

    private Dock dock;
    private Session session;
    private ArrayList<Dock> dockList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dockInfo.setVisible(false);
        setImage(searchImg, Path.SEARCH_ICON);
    }

    public ReturningDockSelectionHandler(Stage stage, String screenPath, ReturningDockSelectionController returningDockSelectionController, Session session) throws IOException {
        super(stage, screenPath);
        setBController(returningDockSelectionController);
        this.session = session;
        dockList = this.getBController().getDockList();
        displayDockList();
    }

    @Override
    public ReturningDockSelectionController getBController() {
        return (ReturningDockSelectionController) super.getBController();
    }

    public void onDockListItemClicked(Dock dock) {
        this.dock = dock;
        dockInfo.setVisible(true);
        setImage(dockImg, dock.getImageURL());
        dockAddress.setText(dock.getLocation());
        dockEmptySlots.setText(Integer.toString(dock.getNumberOfAvailableBike()) + '/' + dock.getCapacity());
    }

    public void displayDockList() {
        vboxDockList.getChildren().clear();
        HBox hbox = createHBox();
        int cnt = 0;
        try {
            for (Dock dock : dockList) {
                if (cnt == 2) {
                    cnt = 0;
                    vboxDockList.getChildren().add(hbox);
                    hbox = createHBox();
                }
                ReturningDockListItemHandler dockListItem = new ReturningDockListItemHandler(Configs.RETURNING_DOCK_LIST_ITEM_PATH, this);
                dockListItem.setDock(dock);
                hbox.getChildren().add(dockListItem.getContent());
                cnt++;
            }
            if (cnt < 2) {
                vboxDockList.getChildren().add(hbox);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private HBox createHBox() {
        HBox hbox = new HBox();
        Insets insets = new Insets(20, 20,20,20);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setPadding(insets);
        hbox.setSpacing(30);
        return hbox;
    }

    @FXML
    void searchImgListener(MouseEvent e) {
        this.dockList = this.getBController().getDockListByKeyword(searchField.getText());
        displayDockList();
    }

    @FXML
    void returnBikeBtnListener(MouseEvent e) {
        if (dock != null) {
            Invoice invoice = new Invoice(this.session.getId());
            InvoiceScreenController invoiceScreenController = new InvoiceScreenController();
            InvoiceScreenHandler invoiceScreenHandler = null;
            try {
                invoiceScreenHandler = new InvoiceScreenHandler(this.stage,
                        Configs.INVOICE_SCREEN_PATH, invoice, invoiceScreenController);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            invoiceScreenHandler.setPreviousScreen(this);
            invoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
            invoiceScreenHandler.setScreenTitle("Invoice Screen");
            invoiceScreenHandler.show();
        }
    }


}
