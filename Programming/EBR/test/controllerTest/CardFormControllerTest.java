package controllerTest;

import controller.CardFormController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardFormControllerTest {

    private CardFormController cardFormController;
//
    @BeforeEach
    void Setup() {
        cardFormController = new CardFormController();
    }

    @ParameterizedTest
    @CsvSource({
            "121319_group5_2020,",
            " , CARD NUMBER IS NOT FILLED!",
            "113546 Hello, INVALID CARD NUMBER"
    })
    public void validateCardNumberTest(String cardNumber, String expected) {
        try {
            cardFormController.validateCardNumber(cardNumber);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Group 5,",
            ", CARD OWNER IS NOT FILLED",
            "Group-5, INVALID CARD OWNER"
    })
    public void validateCardOwnerTest(String cardOwner, String expected) {
        try {
            cardFormController.validateCardOwner(cardOwner);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "721,",
            ", SECURITY CODE IS NOT FILLED",
            "A15, INVALID SECURITY CODE",
            "2251, INVALID SECURITY CODE"
    })
    public void validateSecurityCodeTest(String securityCode, String expected) {
        try {
            cardFormController.validateSecurityCode(securityCode);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1125,",
            ", EXP DATE IS NOT FILLED",
            "1119, INVALID EXP DATE",
            "11/25, INVALID EXP DATE",
            "12/11/2025, INVALID EXP DATE"
    })
    public void validateExpDateTest(String expDate, String expected) {
        try {
            cardFormController.validateExpDate(expDate);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }    }

}
