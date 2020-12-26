package controllerTest.InvocieScreenController;

import controller.InvoiceScreenController;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateReturnedTest {
    InvoiceScreenController invoiceScreenController;
    PaymentTransaction tmpTransaction;
    CreditCard card;

    @BeforeEach
    void Setup() {
        invoiceScreenController = new InvoiceScreenController();
    }
    @ParameterizedTest
    @CsvSource({
            "10, true",
            "-10, false",
            "100000, true"
    })
    public void validateReturnedTest(int returned, boolean expected) {
        assertEquals(expected, invoiceScreenController.validateReturned(returned));
    }



}
