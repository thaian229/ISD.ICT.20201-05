package returning;

import controller.InvoiceScreenController;
import model.bike.Bike;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;

import java.time.LocalDateTime;
public class CalculateTotalFeesTest {
    InvoiceScreenController invoiceScreenController;
    LocalDateTime now;
    PaymentTransaction tmpTransaction;
    CreditCard card;
    Bike bike;
    Session session;

//    @BeforeEach
//    void Setup() {
//        bike = new StandardBike(01);
//        card = new CreditCard("123", "MH", 123, "0824");
//        tmpTransaction = new PaymentTransaction("01", card, "01", "temp", 123, "03/32");
//        invoiceScreenController = new InvoiceScreenController();
//        now = LocalDateTime.now();
//    }
//
//    @Test
//    public void Test1() {
//        session = SessionManager.getInstance().createSession(bike, card, tmpTransaction);
//        System.out.println(session.getStartTime());
//        session.setStartTime(now.minusMinutes(30));
//        Invoice invoice = new Invoice("1",session.getId());
////        invoice.setBikeForTest(bike);
////        invoice.setStartTimeForTest(now.minusMinutes(11));
////        invoice.setEndTimeForTest(now);
////        invoice.getSession().setStartTime(now.minusMinutes(30));
//        System.out.println(invoice.getSession().getStartTime());
//        session.
//        System.out.println(invoice.getSession().getSessionLength());
//        assertEquals(10000, invoiceScreenController.calculateTotalFees(invoice));
//    }
//
//    @Test
//    public void Test2() {
//        Bike bike = new StandardElectricalBike(02);
//        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
//        invoice.setBikeForTest(bike);
//        invoice.setStartTimeForTest(now.minusMinutes(61));
//        invoice.setEndTimeForTest(now);
//        assertEquals(100000, invoiceScreenController.calculateTotalFees(invoice));
//    }
//
//    @Test
//    public void Test3() {
//        Bike bike = new TwinBike(03);
//        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
//        invoice.setBikeForTest(bike);
//        invoice.setStartTimeForTest(now.minusMinutes(2400));
//        invoice.setEndTimeForTest(now);
//        assertEquals(800000, invoiceScreenController.calculateTotalFees(invoice));
//    }
//    @Test
//    public void Test4() {
//        Bike bike = new TwinElectricalBike(04);
//        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
//        invoice.setBikeForTest(bike);
//        invoice.setStartTimeForTest(now.minusMinutes(320));
//        invoice.setEndTimeForTest(now);
//        assertEquals(300000, invoiceScreenController.calculateTotalFees(invoice));
//    }


}
