package model.invoice;



import model.bike.Bike;
import model.db.EBRDB;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;


import java.sql.*;

import java.util.ArrayList;

public class InvoiceManager {
    private static InvoiceManager instance;
    private ArrayList<Invoice> invoiceHistory = new ArrayList<>();

    public InvoiceManager(){
        this.refreshInvoiceHistory();
    }

    public InvoiceManager getInstance(){
        if(instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }

    public Invoice createInvoice(String id, String session_id, int total_charge) {
        Invoice newInvoice = new Invoice(id,session_id,total_charge);
        this.updateInvoiceHistory(newInvoice);
        return newInvoice;
    }

    private void updateInvoiceHistory(Invoice newInvoice) {
        String SQL = "INSERT INTO invoice(id, session_id, total_charge) "
                + "VALUES(?,?,?)";
        try{
            PreparedStatement prestm = EBRDB.getConnection().prepareStatement(SQL);
            prestm.setString(1, newInvoice.getId());
            prestm.setString(2, newInvoice.getSessionId());
            prestm.setInt(3, newInvoice.getTotalFees());
            ResultSet res = prestm.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

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

    public void removeInvoice(Invoice invoice){
        invoiceHistory.remove(invoice);
    }
    public Invoice getInvoiceById(String invoiceId){
        for(Invoice invoice: invoiceHistory){
            if(invoice.getId() == invoiceId) return invoice;
        }
        return null;
    }
}
