package model.invoice;

import model.session.Session;

import java.util.ArrayList;

public class InvoiceManager {
    private static InvoiceManager instance;
    private ArrayList<Invoice> invoices = new ArrayList<>();
    public InvoiceManager(){

    }
    public InvoiceManager getInstance(){
        if(instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }
    public void saveInvoice(Invoice invoice){

    }
}
