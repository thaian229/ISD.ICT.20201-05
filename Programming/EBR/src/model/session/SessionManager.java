package model.session;

import model.bike.Bike;
import model.bike.BikeManager;
import model.db.EBRDB;
import model.payment.creditCard.CreditCard;
import model.payment.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessionManager {
    private static SessionManager instance; //singleton
    private ArrayList<Session> sessions = new ArrayList<>();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Session createSession(Bike bike, CreditCard card, PaymentTransaction rentTransaction) {
        Session newSession = new Session(bike, card, rentTransaction);
        this.updateSessionsData(newSession);
        return newSession;
    }

    private void updateSessionsData(Session newSession) {
        this.sessions.add(newSession);
        //TODO: DB access here
    }

    public Session getSessionById(String id) {
        //TODO: DB access for find sessions by id;
//        for(Session session : sessions) {
//            if (session.getId().equals(id)) {
//                return session;
//            }
//        }
        return null;
    }

    private void updateSessionsList() {
        //TODO: DB access for get all session
        String SQL = "SELECT * FROM session";
        String id = "";
        String bike_id = "";
        String card_id = "";
        String rent_transactionid = "";
        String return_transactionid = "";
        String start_time = "";
        String end_time = "";

        try (Connection conn = EBRDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                id = rs.getString("id");
                bike_id = rs.getString("bike_id");
                card_id = rs.getString("card_id");
                rent_transactionid = rs.getString("rent_transactionid");
                return_transactionid = rs.getString("return_transactionid");
                start_time = rs.getString("id");
                end_time = rs.getString("id");
                Session session = new Session(
                        id,
                        BikeManager.getInstance().getBikeById(bike_id),
                        CreditCardManager.getInstance().getCardById(card_id),
                        LocalDateTime.parse(start_time, Utils.DATE_FORMATER),
                        LocalDateTime.parse(end_time, Utils.DATE_FORMATER),
                        PaymentTransactionManager.getInstance().getTransactionById(rent_transactionid),
                        PaymentTransactionManager.getInstance().getTransactionById(return_transactionid)
                        );

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
