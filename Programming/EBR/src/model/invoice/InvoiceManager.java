package model.invoice;



import model.db.EBRDB;
import model.session.Session;
import utils.Utils;

import java.sql.*;
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
    public InvoiceManager getInstance(){
        if(instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }
    /**
     * This method is for create new Invoice object and update it details to the DB
     *
     * @param id            invoice_id
     * @param session_id    session_id
     * @param total_charge totalFees
     * @return newInvoice new Invoice object
     * @author khang
     */
    public Invoice createInvoice(String id, String session_id, int total_charge) {
        Invoice newInvoice = new Invoice(id,session_id,total_charge);
        this.updateInvoiceHistory(newInvoice);
        return newInvoice;
    }
    /**
     * for inserting new invoice to DB
     *
     * @param newInvoice
     * @return id new record id
     * @author khang
     */
    private String updateInvoiceHistory(Invoice newInvoice) {
        String SQL = "INSERT INTO session(id, session_id, total_charge) "
                + "VALUES(?,?,?)";
        String id = "";

        // Insert new row
        try (
                PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL);
        ) {
            // Set up parameters
            pstmt.setString(1, newInvoice.getId());
            pstmt.setString(2, newInvoice.getSessionId());
            pstmt.setInt(3, newInvoice.getTotalFees());
            // Handle update
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    /**
     * @author khang
     * get invoice history from DB and store them to the list
     */
    public void refreshInvoiceHistory() {
        this.invoiceHistory.clear();

        // query for invoice history
        String SQL = "SELECT * FROM invoice";

        try (Connection conn = EBRDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Invoice newInvoice = new Invoice(rs.getString("id"),
                        rs.getString("session_id"),
                        rs.getInt("total_charge"));
                invoiceHistory.add(newInvoice);
            }
        } catch (SQLException ex) {
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
     * @return invoice
     */
    public void removeInvoice(Invoice invoice){
        invoiceHistory.remove(invoice);
    }

    /**
     * get invoice object by passing an id
     *
     * @param invoiceId
     * @return invoice Invoice object
     * @author khang
     */
    public Invoice getInvoiceById(String invoiceId){
        for(Invoice invoice: invoiceHistory){
            if(invoice.getId() == invoiceId) return invoice;
        }
        return null;
    }
}
