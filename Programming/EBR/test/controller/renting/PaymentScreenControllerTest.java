package controller.renting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentScreenControllerTest {

    private PaymentScreenController paymentScreenController;

    @BeforeEach
    void Setup() {
        paymentScreenController = new PaymentScreenController();
    }

    @ParameterizedTest
    @CsvSource({
            "121319_group5_2020, true",
            ", false",
            "113546 Hello, false"
    })
    public void validateCardNumberTest(String cardNumber, boolean expected) {
        assertEquals(expected, paymentScreenController.validateCardNumber(cardNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "Group 5, true",
            ", false",
            "Group-5, false"
    })
    public void validateCardOwnerTest(String cardOwner, boolean expected) {
        assertEquals(expected, paymentScreenController.validateCardOwner(cardOwner));
    }

    @ParameterizedTest
    @CsvSource({
            "721, true",
            ", false",
            "A15, false",
            "2251, false"
    })
    public void validateSecurityCodeTest(String securityCode, boolean expected) {
        assertEquals(expected, paymentScreenController.validateSecurityCode(securityCode));
    }

    @ParameterizedTest
    @CsvSource({
            "1125, true",
            ", false",
            "1119, false",
            "11/25, false",
            "12/11/2025, false"
    })
    public void validateExpDateTest(String expDate, boolean expected) {
        assertEquals(expected, paymentScreenController.validateExpDate(expDate));
    }

}
