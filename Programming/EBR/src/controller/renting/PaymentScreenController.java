package controller.renting;

import controller.BaseController;

import java.util.HashMap;

/**
 * Controller to execute all logic required for taking payment method
 * and start a new renting session
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 25/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class PaymentScreenController extends BaseController {

    /**
     * logic to handle credit card info form submit
     */
    public void processCreditCardSubmit() {

    }

    /**
     * validating format of card info form
     * @param creditCardForm all fields of form forward from View
     */
    public void validateCreditCardForm(HashMap<String, String> creditCardForm) {

    }

    /**
     * validate card number only contains digits, letters and underscore
     * @param cardNumber string of card number
     * @return validation result
     */
    public boolean validateCardNumber(String cardNumber) {
        // check card number is not empty
        if (cardNumber == null) return false;
        if (cardNumber.length() == 0) return false;

        // check card number contains only letters, digits and underscores
        return cardNumber.matches("[0-9a-zA-Z_]+");
    }

    /**
     * validate card owner only contains digits, letters and spaces
     * @param cardOwner string of card owner
     * @return validation result
     */
    public boolean validateCardOwner(String cardOwner) {
        // check card owner is not empty
        if (cardOwner == null) return false;
        if (cardOwner.length() == 0) return false;

        // check card owner contains only letters, digits and spaces
        return cardOwner.matches("[0-9a-zA-Z ]+");
    }

    /**
     * validate security code only contains digits and length is 3
     * @param securityCode string of security code
     * @return validation result
     */
    public boolean validateSecurityCode(String securityCode) {
        // check security code is not empty
        if (securityCode == null) return false;

        // check security code exactly 3
        if (securityCode.length() != 3) return false;

        // check card owner contains only digits
        return securityCode.matches("[0-9]+");
    }

    /**
     * validate expiration date has correct format MMyy and not yet reached
     * @param expDate string of expiration date
     * @return validation result
     */
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
