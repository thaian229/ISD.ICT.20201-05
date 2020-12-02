package model.invoice;

import model.session.Session;
import model.bike.Bike;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.SessionManager;

import java.time.LocalDateTime;


public class Invoice {
   private String id;
   private String sessionId;
   private int totalFees;
   private int returned;




//    public Invoice(Session session, Bike bike, CreditCard card, PaymentTransaction returnPaymentTransaction) {
//        this.id = "Id return from db";
//        //need invoice id
//        this.session = session;
//        this.bike = bike;
//        this.card = card;
//        this.returnPaymentTransaction = returnPaymentTransaction;
//        this.deposit = 1;
//        this.totalFees =1;
//        this.rentingFees =1;
   // }

    public Invoice(String id, String session_id, int total_charge) {
        this.id =id;
        this.sessionId = session_id;
        this.totalFees = total_charge;
    }
   public Session getSession(){
        return SessionManager.getInstance().getSessionById(sessionId);
   }

    public Bike getBike(){
        return SessionManager.getInstance().getSessionById(sessionId).getBike();
    }
    public CreditCard getCard(){
        return SessionManager.getInstance().getSessionById(sessionId).getCard();
    }
    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }


    public LocalDateTime getStartTime() {
        return SessionManager.getInstance().getSessionById(sessionId).getStartTime();
    }


    public String getId(){
       return id;
    }
    public LocalDateTime getEndTime() {
        return SessionManager.getInstance().getSessionById(sessionId).getEndTime();
    }

    public int getDeposit() {
        return SessionManager.getInstance().getSessionById(sessionId).getBike().getDeposit();
    }


    public int getTotalFees(){
       return totalFees;
    }

    public PaymentTransaction getReturnPaymentTransaction() {
        return SessionManager.getInstance().getSessionById(sessionId).getReturnTransaction();
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned() {
        this.returned = this.getDeposit() - this.totalFees;
    }



    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id.equals(invoice.id) &&
                sessionId.equals(invoice.sessionId) &&
                totalFees == invoice.totalFees;
    }

}
