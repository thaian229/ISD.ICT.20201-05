package model.invoice;

import model.session.Session;
import model.bike.Bike;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.SessionManager;

import java.time.LocalDateTime;



/**
 * class for the model Invoice
 *
 * @author khang
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

public class Invoice {
   private String id;
   private String sessionId;
   private int totalFees;
 //  private int returned;




    public Invoice(String session_id){
        this.sessionId = session_id;
    }
   public Invoice(String id, String session_id, int total_charge) {
        this.id = id;
        this.sessionId = session_id;
        this.totalFees = total_charge;
    }


    /**
     * This method is used to get session by using session_id
     * @return session
     */
    public Session getSession(){
        return SessionManager.getInstance().getSessionById(sessionId);
    }


    /**
     * This method is used to get bike by using session_id
     * @return bike
     */
    public Bike getBike(){
        return SessionManager.getInstance().getSessionById(sessionId).getBike();
    }

    /**
     * This method is used to get credit card by using session_id
     * @return card
     */
    public CreditCard getCard(){
        return SessionManager.getInstance().getSessionById(sessionId).getCard();
    }
    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    /**
     * This method is used to get start time by using session_id
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return SessionManager.getInstance().getSessionById(sessionId).getStartTime();
    }


    public String getId(){
       return id;
    }
    public String getSessionId(){
        return sessionId;
    }


    /**
     * This method is used to get end time by using session_id
     * @return endTime
     */
    public LocalDateTime getEndTime() {
        return SessionManager.getInstance().getSessionById(sessionId).getEndTime();
    }

    /**
     * This method is used to get deposit by using session_id
     * @return deposit
     */

    public int getDeposit() {
        return SessionManager.getInstance().getSessionById(sessionId).getBike().getDeposit();
    }


    public int getTotalFees(){
       return totalFees;
    }



    /**
     * This method is used to get return transaction by using session_id
     * @return returnTransaction
     */

    public PaymentTransaction getReturnPaymentTransaction() {
        return SessionManager.getInstance().getSessionById(sessionId).getReturnTransaction();
    }

//    public int getReturned() {
//        return returned;
//    }
//
//    public void setReturned(int returned) {
//        this.returned = returned;
//    }

    /**
     * This method is for comparing 2 Invoice objects
     *
     * @param o object
     * @return true if equals
     * @author khang
     */

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return
                sessionId.equals(invoice.sessionId);

    }



    //For test


    private int depositForTest;
    private Bike bikeForTest;
    private LocalDateTime startTimeForTest;
    private LocalDateTime endTimeForTest;

    public void setDepositForTest(int depositForTest){
        this.depositForTest = depositForTest;
    }
    public int getDepositForTest(){
        return depositForTest;
    }
    public void setBikeForTest(Bike bikeForTest){
        this.bikeForTest = bikeForTest;
    }

    public Bike getBikeForTest(){
        return bikeForTest;
    }

    public LocalDateTime getStartTimeForTest() {
        return startTimeForTest;
    }

    public void setStartTimeForTest(LocalDateTime startTimeForTest) {
        this.startTimeForTest = startTimeForTest;
    }

    public LocalDateTime getEndTimeForTest() {
        return endTimeForTest;
    }

    public void setEndTimeForTest(LocalDateTime endTimeForTest) {
        this.endTimeForTest = endTimeForTest;
    }




}
