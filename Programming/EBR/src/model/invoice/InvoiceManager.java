package model.invoice;



import model.db.EBRDB;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InvoiceManager {
    private static InvoiceManager instance;
    private ArrayList<Invoice> invoiceHistory;
    public InvoiceManager(){
        this.invoiceHistory = new ArrayList<>();
    }

    public InvoiceManager getInstance(){
        if(instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }
    public void refreshInvoiceHistory() {
        this.invoiceHistory.clear();

        // query for all Docks
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
