package model.invoice;

import model.session.Session;
import model.bike.Bike;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;

import java.time.LocalDateTime;


public class Invoice {
   private String id;
   private Session session;
   private String sessionId;
   private  Bike bike;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private int deposit;
   private int totalFees;
   private int returned;
   private int rentingFees;
   private CreditCard card;
   private PaymentTransaction returnPaymentTransaction;


    public Invoice(Session session, Bike bike, CreditCard card, PaymentTransaction returnPaymentTransaction) {
        this.id = "Id return from db";
        //need invoice id
        this.session = session;
        this.bike = bike;
        this.card = card;
        this.returnPaymentTransaction = returnPaymentTransaction;
//        this.deposit = 1;
//        this.totalFees =1;
//        this.rentingFees =1;
    }
    public  Bike getBike() {
        return bike;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    public void setSessionId(){
       this.sessionId = session.getId();
    }
    public void setStartTime() {
        this.startTime = session.getStartTime();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getId(){
       return id;
    }
    public void setEndTime() {
        this.endTime = session.getEndTime();
    }

    public int getDeposit() {
        return deposit;
    }

    public int getTotalFees(){
       return totalFees;
    }

    public PaymentTransaction getReturnPaymentTransaction() {
        return returnPaymentTransaction;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned() {
        this.returned = this.deposit - this.totalFees;
    }

    public int getRentingFees() {
        return rentingFees;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return bike.equals(invoice.bike) &&
                card.equals(invoice.card) &&
                startTime.equals(invoice.startTime);
    }

}
