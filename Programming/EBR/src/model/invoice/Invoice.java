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
 * created_at: 20/12/2020
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

    /**
     * Constructor using session id
     * @param session_id id of chosen session
     */
    public Invoice(String session_id){
        this.sessionId = session_id;
    }

    /**
     * Constructor
     * @param id invoice id
     * @param sessionId session id
     */
    public Invoice(String id, String sessionId){
        this.id = id;
        this.sessionId = sessionId;
    }

    /**
     * Constructor
     * @param id invoice id
     * @param session_id session id
     * @param total_charge total charge of renting session
     */
   public Invoice(String id, String session_id, int total_charge) {
        this.id = id;
        this.sessionId = session_id;
        this.totalFees = total_charge;
    }



    public void setId(String id) {
        this.id = id;
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
    public Bike getBike() {
        return SessionManager.getInstance().getSessionById(sessionId).getBike();
    }

    /**
     * This method is used to get credit card by using session_id
     * @return card
     */
    public CreditCard getCard() {
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
        return LocalDateTime.now();
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return
                sessionId.equals(invoice.sessionId);

    }







}
