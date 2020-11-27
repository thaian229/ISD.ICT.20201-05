package model.invoice;

import model.session.Session;
import model.bike.Bike;
import model.payment.CreditCard;
import model.session.Session;
import utils.Utils;
import model.payment.PaymentTransaction



public class Invoice {
   private String id;
   private Session session;
   private String sessionId;
   private Bike bike;
   private String startTime;
   private String endTime;
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
    }

    public void setSessionId(){
       this.sessionId = session.Id;
    }
    public void setStartTime() {
        this.startTime = session.startTime;
    }

    public void setEndTime() {
        this.endTime = session.endTime;
    }

    public int getDeposit() {
        return deposit;
    }
    public void setDeposit(){
       this.deposit = session.deposit;
    }
    public int getTotalFees(){
       return totalFees;
    }
    public void setTotalFees(){
       this.totalFees = session.totalFees;
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

    public void setRentingFees() {
        this.rentingFees = session.retingFees;
    }
}
