package model.session;

import model.bike.Bike;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Session {
    private String id;
    private Bike bike;
    private CreditCard card;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private PaymentTransaction rentTransaction;
    private PaymentTransaction returnTransaction;

    public Session(Bike bike, CreditCard card, PaymentTransaction rentTransaction) {
        this.bike = bike;
        this.card = card;
        this.rentTransaction = rentTransaction;
        this.startTime = LocalDateTime.now();
        //TODO: DB CREATE NEW SESSION ROW AND RETURN ID
        this.id = "id returned from db";
    }

    public Session(String id, Bike bike, CreditCard card, LocalDateTime startTime, LocalDateTime endTime, PaymentTransaction rentTransaction, PaymentTransaction returnTransaction) {
        this.bike = bike;
        this.card = card;
        this.rentTransaction = rentTransaction;
        this.startTime = startTime;
        this.endTime = endTime;
        this.returnTransaction = returnTransaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public PaymentTransaction getRentTransaction() {
        return rentTransaction;
    }

    public PaymentTransaction getReturnTransaction() {
        return returnTransaction;
    }

    public void setReturnTransaction(PaymentTransaction returnTransaction) {
        this.returnTransaction = returnTransaction;
    }

    public HashMap<String, String> getSessionInfo() {
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("bike", "" + bike.getBarcode());
        info.put("card", card.getCardNum());
        info.put("startTime", startTime.format(Utils.DATE_FORMATER));
        info.put("endTime", endTime.format(Utils.DATE_FORMATER));
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return bike.equals(session.bike) &&
                card.equals(session.card) &&
                startTime.equals(session.startTime);
    }
}
