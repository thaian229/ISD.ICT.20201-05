package model.invoice;


import model.bike.Bike;
import model.payment.CreditCard;
import model.payment.PaymentTransaction;
import model.session.Session;

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
    public ArrayList<Invoice> getInvoiceHistory(){
        return invoiceHistory;
    }
    public Invoice createInvoice(Session session, Bike bike, CreditCard card, PaymentTransaction returnPaymentTransaction){
        Invoice newInvoice = new Invoice(session,bike,card,returnPaymentTransaction);
        this.addInvoice(newInvoice);
        return newInvoice;
    }
    public void setInvoiceHistory(ArrayList<Invoice> invoiceHistory) {
        this.invoiceHistory = invoiceHistory;
    }
    public void addInvoice(Invoice newInvoice){
        invoiceHistory.add(newInvoice);
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
