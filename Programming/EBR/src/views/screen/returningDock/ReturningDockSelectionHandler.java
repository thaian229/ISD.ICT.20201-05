package views.screen.returningDock;

import controller.ReturningDockSelectionController;
import controller.InvoiceScreenController;
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
import model.invoice.Invoice;
import model.invoice.InvoiceManager;
import model.session.Session;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

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
    private VBox vboxDockList1;
    @FXML
    private VBox vboxDockList2;
    @FXML
    private AnchorPane dockInfo;
    @FXML
    private ImageView searchImg;
    @FXML
    private ImageView back;
    @FXML
    private ImageView logo;

    private Dock dock;
    private Session session;
    private ArrayList<Dock> dockList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dockInfo.setVisible(false);
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> this.getPreviousScreen().show());
        this.setImages();
    }

    public ReturningDockSelectionHandler(Stage stage, String screenPath, ReturningDockSelectionController returningDockSelectionController, Session session) throws IOException {
        super(stage, screenPath);
        setBController(returningDockSelectionController);
        this.session = session;
        dockList = this.getBController().getDockList();
        displayDockList();
    }

    private void setImages() {
        try {
            setImage(logo, Path.LOGO_ICON);
            setImage(back, Path.BACK_NAV_ICON);
            setImage(searchImg, Path.SEARCH_ICON);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
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
        vboxDockList1.getChildren().clear();
        vboxDockList2.getChildren().clear();
        try {
            for (Dock dock : dockList) {
                ReturningDockListItemHandler dockListItem = new ReturningDockListItemHandler(Path.RETURNING_DOCK_LIST_ITEM_PATH, this);
                dockListItem.setDock(dock);
                if (dockList.indexOf(dock) % 2 == 0)
                    vboxDockList1.getChildren().add(dockListItem.getContent());
                else vboxDockList2.getChildren().add(dockListItem.getContent());

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    private HBox createHBox() {
//        HBox hbox = new HBox();
//        Insets insets = new Insets(20, 20, 20, 20);
//        hbox.setAlignment(Pos.BASELINE_CENTER);
//        hbox.setPadding(insets);
//        hbox.setSpacing(30);
//        return hbox;
//    }

    @FXML
    void searchImgListener(MouseEvent e) {
        this.dockList = this.getBController().getDockListByKeyword(searchField.getText());
        displayDockList();
    }

    @FXML
    void returnBikeBtnListener(MouseEvent e) {
        if (dock != null) {
            BikeManager.getInstance().getBikeById(this.session.getBike().getId()).putBikeInDock(this.dock);
            Invoice invoice = InvoiceManager.getInstance().createInvoice(session.getId());
            InvoiceScreenController invoiceScreenController = new InvoiceScreenController();
            InvoiceScreenHandler invoiceScreenHandler = null;
            try {
                invoiceScreenHandler = new InvoiceScreenHandler(this.stage,
                        Path.INVOICE_SCREEN_PATH, invoice, invoiceScreenController);
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
