package controller.renting;

import controller.BaseController;

import java.util.HashMap;

public class PaymentScreenController extends BaseController {

    public void processCreditCardSubmit() {

    }

    public void validateCreditCardForm(HashMap<String, String> creditCardForm) {

    }

    public boolean validateCardNumber(String cardNumber) {
        // check card number is not empty
        if (cardNumber == null) return false;
        if (cardNumber.length() == 0) return false;

        // check card number contains only letters, digits and underscores
        return cardNumber.matches("[0-9a-zA-Z_]+");
    }

    public boolean validateCardOwner(String cardOwner) {
        // check card owner is not empty
        if (cardOwner == null) return false;
        if (cardOwner.length() == 0) return false;

        // check card owner contains only letters, digits and spaces
        return cardOwner.matches("[0-9a-zA-Z ]+");
    }

    public boolean validateSecurityCode(String securityCode) {
        // check security code is not empty
        if (securityCode == null) return false;

        // check security code exactly 3
        if (securityCode.length() != 3) return false;

        // check card owner contains only digits
        return securityCode.matches("[0-9]+");
    }

    public boolean validateExpDate(String expDate) throws NullPointerException {
        // check expire date is not empty
        if (expDate == null) return false;

        // check expire date exactly 4
        if (expDate.length() != 4) return false;

        // check card owner contains only digits
        if (!expDate.matches("[0-9]+")) return false;

        try {
            int month = Integer.parseInt(expDate.substring(0,2));
            int year = Integer.parseInt(expDate.substring(2,4));

            if (month < 1 || month > 12 || year < 20 || year > 99) return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
