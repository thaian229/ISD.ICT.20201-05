package model.invoice;

import model.session.Session;

import java.util.ArrayList;

public class InvoiceHistory {
    private static InvoiceHistory instance;
    private ArrayList<Invoice> invoiceHistory;
    public InvoiceHistory(){
        this.invoiceHistory = new ArrayList<>();
    }

    public InvoiceHistory getInstance(){
        if(instance == null){
            instance = new InvoiceHistory();
        }
        return instance;
    }
    public ArrayList<Invoice> getInvoiceHistory(){
        return invoiceHistory;
    }

    public void setInvoiceHistory(ArrayList<Invoice> invoiceHistory) {
        this.invoiceHistory = invoiceHistory;
    }
    public void addInvoice(Invoice invoice){
        invoiceHistory.add(invoice);
    }
    public void removeInvoice(Invoice invoice){
        invoiceHistory.remove(invoice);
    }
    public Invoice searchInvoiceById(String invoiceId){
        for(Invoice invoice: invoiceHistory){
            if(invoice.getId() == invoiceId) return invoice;
        }
        return null;
    }
}
