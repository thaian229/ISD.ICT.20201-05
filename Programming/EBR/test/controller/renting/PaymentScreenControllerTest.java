package controller.renting;

import controller.PaymentScreenController;
import model.bike.StandardBike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PaymentScreenControllerTest {

    private PaymentScreenController paymentScreenController;
//
    @BeforeEach
    void Setup() {
        StandardBike bike = new StandardBike(1111);
        paymentScreenController = new PaymentScreenController(bike);
    }

    @ParameterizedTest
    @CsvSource({
            "121319_group5_2020,",
            " , ERROR: Card number is not filled!",
            "113546 Hello, 'INVALID: Card number must contains only letters, digits and underscores!'"
    })
    public void validateCardNumberTest(String cardNumber, String expected) {
        try {
            paymentScreenController.validateCardNumber(cardNumber);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Group 5,",
            ", ERROR: Card owner is not filled!",
            "Group-5, 'INVALID: Card owner must contains only letters, digits and space!'"
    })
    public void validateCardOwnerTest(String cardOwner, String expected) {
        try {
            paymentScreenController.validateCardOwner(cardOwner);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "721,",
            ", ERROR: Security code is not found!",
            "A15, INVALID: Security code must contains only 3 digits!",
            "2251, INVALID: Security code must contains only 3 digits!"
    })
    public void validateSecurityCodeTest(String securityCode, String expected) {
        try {
            paymentScreenController.validateSecurityCode(securityCode);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1125,",
            ", ERROR: Expiry date is not filled!",
            "1119, INVALID: Card is expired!",
            "11/25, INVALID: Expiry date must contains only 4 digits!",
            "12/11/2025, INVALID: Expiry date must contains only 4 digits!"
    })
    public void validateExpDateTest(String expDate, String expected) {
        try {
            paymentScreenController.validateExpDate(expDate);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }    }

}
