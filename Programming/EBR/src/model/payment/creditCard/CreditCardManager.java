package model.payment.creditCard;


public class CreditCardManager {

    private static CreditCardManager instance;

    public static CreditCardManager getInstance() {
        if (instance == null) {
            instance = new CreditCardManager();
        }
        return instance;
    }


    public CreditCard getCardById(String card_id) {
        return null;
    }
}
