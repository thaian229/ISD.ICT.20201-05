package views.screen.invoice;

import controller.renting.SessionScreenController;
import controller.returning.InvoiceScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.bike.TwinElectricalBike;
import model.invoice.Invoice;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import utils.Path;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handler for the Invoice Screen
 *
 * @author khang
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

public class InvoiceScreenHandler extends BaseScreenHandler implements Initializable {
    private Invoice invoice;
    private InvoiceScreenController controller;
    @FXML
    ImageView logo;

    @FXML
    ImageView invoiceBikeImage;

    @FXML
    Text invoiceCardNumber;


    @FXML
    Text invoiceStartTime;

    @FXML
    Text invoiceEndTime;

    @FXML
    Text invoiceSessionLength;

    @FXML
    Text invoiceDeposit;

    @FXML
    Text invoiceTotalFees;

    @FXML
    Text invoiceReturned;

    @FXML
    Button returnHomeButton;



    public InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice, InvoiceScreenController controller) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.controller = controller;
        this.setImages();
        this.setTextFields();

        returnHomeButton.setOnMouseClicked(e -> {
            try {
                handleReturnHome();
                homeScreenHandler.show();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
    }
    private void setImages() {
        try {
            File file = new File(this.invoice.getBike().getImageURL());
            Image image = new Image(file.toURI().toString());
            invoiceBikeImage.setImage(image);

            file = new File(Path.LOGO_ICON);
            image = new Image(file.toURI().toString());
            logo.setImage(image);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    private void setTextFields() {
        try {
            CreditCard card = this.invoice.getCard();
            invoiceCardNumber.setText(card.getCardNum());
            invoiceStartTime.setText(this.invoice.getStartTime().toString());
            invoiceEndTime.setText(this.invoice.getEndTime().toString());

            invoiceSessionLength.setText(Long.toString(controller.calculateSessionLength(this.invoice)));
            invoiceDeposit.setText(Integer.toString(invoice.getDeposit()));
            invoiceTotalFees.setText(Integer.toString(controller.calculateTotalFees(this.invoice)));
            invoiceReturned.setText(controller.calculateReturned(this.invoice) + " VND");

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }
    private void handleReturnHome() throws IOException{
        String contents = "refund";
        PaymentTransaction returnTransaction = this.controller.refund(this.controller.calculateReturned(this.invoice), contents,
                this.invoice.getCard().getCardNum(), this.invoice.getCard().getCardOwner(),
                this.invoice.getCard().getExpDate(), Integer.toString(this.invoice.getCard().getSecurityCode()));
    }
}
