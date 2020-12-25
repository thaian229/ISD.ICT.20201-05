package model.session;

import model.bike.Bike;
import model.payment.paymentCard.PaymentCard;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * class for the model Session
 *
 * @author mHoang, Nguyen Thai An
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

public class Session {

    private String id;
    private Bike bike;
    private CreditCard card;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime lastResumeTime;
    private int lastRentTimeBeforeLock = 0;
    private boolean active;
    private PaymentTransaction rentTransaction;
    private PaymentTransaction returnTransaction;

    public Session(Bike bike, CreditCard card, PaymentTransaction rentTransaction) {
        this.bike = bike;
        this.card = card;
        this.rentTransaction = rentTransaction;
        this.startTime = LocalDateTime.now();
        this.lastResumeTime = this.startTime;
    }

    public Session(String id, Bike bike, CreditCard card, LocalDateTime startTime, PaymentTransaction rentTransaction) {
        this.id = id;
        this.bike = bike;
        this.card = card;
        this.startTime = startTime;
        this.rentTransaction = rentTransaction;
    }

    public Session(String id, Bike bike, CreditCard card, LocalDateTime startTime, LocalDateTime endTime, PaymentTransaction rentTransaction, PaymentTransaction returnTransaction) {
        this.id = id;
        this.bike = bike;
        this.card = card;
        this.startTime = startTime;
        this.rentTransaction = rentTransaction;
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

    public LocalDateTime getLastResumeTime() {
        return lastResumeTime;
    }

    public void setLastResumeTime(LocalDateTime lastResumeTime) {
        this.lastResumeTime = lastResumeTime;
    }

    public int getLastRentTimeBeforeLock() {
        return lastRentTimeBeforeLock;
    }

    public void setLastRentTimeBeforeLock(int lastRentTimeBeforeLock) {
        this.lastRentTimeBeforeLock = lastRentTimeBeforeLock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * this method is to provide structured string map for display
     *
     * @return info HashMap of information
     * @author mHoang
     */
    public HashMap<String, String> getSessionInfo() {
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("bike", "" + bike.getBarcode());
        info.put("startTime", startTime.format(Utils.DATE_FORMATER));
        info.put("endTime", endTime.format(Utils.DATE_FORMATER));
        return info;
    }

    public long getSessionLength() {
        if (this.active && this.getEndTime() == null) {
            return this.lastRentTimeBeforeLock + Utils.minusLocalDateTime(lastResumeTime, LocalDateTime.now());
        } else if (!this.active && this.getEndTime() == null) {
            return this.lastRentTimeBeforeLock;
        } else {
            return this.lastRentTimeBeforeLock + Utils.minusLocalDateTime(lastResumeTime, endTime);
        }
    }

    /**
     * This method is for comparing 2 Session objects
     *
     * @param o object
     * @return true if equals
     * @author mHoang
     */
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
