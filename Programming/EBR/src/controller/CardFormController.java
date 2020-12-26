package controller;

import common.exception.cardException.*;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * class for ...
 *
 * @author mHoang
 * <p>
 * created_at: 25/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class CardFormController {
    /**
     * validating format of card info form
     *
     * @param creditCardForm all fields of form forward from View
     */
    public static void validateCreditCardForm(HashMap<String, String> creditCardForm) throws FormException{
        validateCardOwner(creditCardForm.get("cardOwner"));
        validateCardNumber(creditCardForm.get("cardNumber"));
        validateExpDate(creditCardForm.get("expDate"));
        validateSecurityCode(creditCardForm.get("securityCode"));
    }

    /**
     * validate card number only contains digits, letters and underscore
     *
     * @param cardNumber string of card number
     */
    public static void validateCardNumber(String cardNumber) {
        // check card number is not empty
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new NullCardNumberException();
        }
        if (cardNumber.length() == 0 || !cardNumber.matches("[0-9a-zA-Z_]+"))
            throw new InvalidCardNumberFormatException();

    }

    /**
     * validate card owner only contains digits, letters and spaces
     *
     * @param cardOwner string of card owner
     */
    public static void validateCardOwner(String cardOwner) {
        // check card owner is not empty
        if (cardOwner == null|| cardOwner.isBlank()) throw new NullCardOwnerException();
        if (cardOwner.length() == 0 || !cardOwner.matches("[0-9a-zA-Z ]+")) throw new InvalidCardOwnerFormatException();
    }

    /**
     * validate security code only contains digits and length is 3
     *
     * @param securityCode string of security code
     */
    public static void validateSecurityCode(String securityCode) {
        // check security code is not empty
        if (securityCode == null|| securityCode.isBlank()) throw new NullSecurityCodeException();

        // check security code exactly 3
        // check card owner contains only digits
        if (securityCode.length() != 3 || !securityCode.matches("[0-9]+")) throw new InvalidSecurityCodeFormatException();

    }

    /**
     * validate expiration date has correct format MMyy and not yet reached
     *
     * @param expDate string of expiration date
     */
    public static void validateExpDate(String expDate) throws NullPointerException {
        // check expire date is not empty
        if (expDate == null || expDate.isBlank()) throw new NullExpDateException();

        // check expire date exactly 4
        if (expDate.length() != 4 || !expDate.matches("[0-9]+")) throw new InvalidExpDateFormatException();

        try {
            int month = Integer.parseInt(expDate.substring(0, 2));
            int year = Integer.parseInt(expDate.substring(2, 4));

            if (month < 1 || month > 12 || year < LocalDateTime.now().getYear() % 100 || year > 99) throw new InvalidExpDateFormatException();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
