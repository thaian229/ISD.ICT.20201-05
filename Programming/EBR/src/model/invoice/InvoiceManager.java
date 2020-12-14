package model.invoice;



import model.bike.BikeManager;
import model.db.EBRDB;
import model.payment.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import model.session.Session;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * class for accessing DB related functions and managing list of Invoice
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


public class InvoiceManager {
    private static InvoiceManager instance;
    private ArrayList<Invoice> invoiceHistory = new ArrayList<>();
    /**
     * @author khang
     * This constructor will refresh the Invoice list when the object is initialized
     */
    public InvoiceManager(){
        this.refreshInvoiceHistory();
    }


    /**
     * This is the InvoiceManager provider
     *
     * @return instance InvoiceManager object
     * @author khang
     */
    public static InvoiceManager getInstance(){
        if(instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }
    /**
     * This method is for create new Invoice object and update it details to the DB
     *
     * @param session_id    session_id
     * @return newInvoice new Invoice object
     * @author khang
     */
    public Invoice createInvoice(String session_id) {
        Invoice newInvoice = new Invoice(session_id);
       // newInvoice.setTotalFees(total_charge);
        this.updateInvoiceHistory(newInvoice);
        this.refreshInvoiceHistory();
        return newInvoice;
    }

    public int finalInvoice(Invoice invoice, int total_charge) {
        invoice.setTotalFees(total_charge);

        String SQL = "UPDATE invoice "
                + "SET (total_charge) = row(?) "
                + "WHERE id = ?::uuid ";

        int affectedRows = 0;

        try (PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, invoice.getTotalFees());
            pstmt.setString(2, invoice.getId());

            affectedRows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }
    /**
     * for inserting new invoice to DB
     *
     * @param newInvoice
     * @return id new record id
     * @author khang
     */
    private String updateInvoiceHistory(Invoice newInvoice) {
        String SQL = "INSERT INTO invoice(session_id) "
                + "VALUES(?::uuid)";
        String id = "";

        // Insert new row
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Set up parameters
            pstmt.setString(1, newInvoice.getSessionId());


            // Handle update
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        newInvoice.setId(id);
        return id;
    }

    /**
     * @author khang
     * get invoice history from DB and store them to the list
     */
    public void refreshInvoiceHistory() {
        invoiceHistory.clear();
        String SQL = "SELECT * FROM invoice";

        // Get All Rows
        try (Statement stmt = EBRDB.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String session_id = rs.getString("session_id");
                int total_charge = rs.getInt("total_charge");

                Invoice invoice ;
                invoice = new Invoice(id,session_id,total_charge);

                if (total_charge==0) {
                    invoice = new Invoice(
                            id,
                           session_id
                    );
                } else {
                    invoice = new Invoice(
                            id,
                            session_id,
                            total_charge
                    );
                }
                invoiceHistory.add(invoice);
            }
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
        }
    }


    public ArrayList<Invoice> getInvoiceHistory(){
        return invoiceHistory;
    }

    public void setInvoiceHistory(ArrayList<Invoice> invoiceHistory) {
        this.invoiceHistory = invoiceHistory;
    }

    /**
     * Remove invoice in history
     * @param invoice invoice to be removed
     */
    public void removeInvoice(Invoice invoice){
        invoiceHistory.remove(invoice);
    }

    /**
     * get invoice object by passing an id
     *
     * @param invoiceId invoice's uuid
     * @return invoice Invoice object
     * @author khang
     */
    public Invoice getInvoiceById(String invoiceId){
        for(Invoice invoice: invoiceHistory){
            if(invoice.getId() == invoiceId) return invoice;
        }
        return null;
    }
    public Invoice getInvoiceBySessionId(String sessionId){
        for(Invoice invoice: invoiceHistory){
            if(invoice.getSessionId() == sessionId) return invoice;
        }
        return null;
    }
}
