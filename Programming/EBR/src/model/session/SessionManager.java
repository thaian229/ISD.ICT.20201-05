package model.session;

import model.bike.Bike;
import model.bike.BikeManager;
import model.db.EBRDB;
import model.dock.Dock;
import model.payment.creditCard.CreditCard;
import model.payment.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessionManager {
    private static SessionManager instance; //singleton
    private ArrayList<Session> sessions = new ArrayList<>();

    private SessionManager() {
        this.refreshSessionsList();
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
        String SQL = "INSERT INTO session(bikeId, cardId, rentTransactionId) "
                + "VALUES(?,?,?)";
        try{
            PreparedStatement prestm = EBRDB.getConnection().prepareStatement(SQL);
            prestm.setString(1, newSession.getBike().getId());
            prestm.setString(2, newSession.getCard().getId());
            prestm.setString(3, newSession.getRentTransaction().getId());
            ResultSet res = prestm.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Session getSessionById(String id) {
        for (Session session: sessions) {
            if (session.getId().equals(id))
                return session;
        }
        return null;
    }

    private void refreshSessionsList() {
        sessions.clear();
        String SQL = "SELECT * FROM session";
        try (Connection conn = EBRDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String bike_id = rs.getString("bike_id");
                String card_id = rs.getString("card_id");
                String rent_transactionid = rs.getString("rent_transactionid");
                String return_transactionid = rs.getString("return_transactionid");
                String start_time = rs.getString("id");
                String end_time = rs.getString("id");
                Session session = new Session(
                        id,
                        BikeManager.getInstance().getBikeById(bike_id),
                        CreditCardManager.getInstance().getCardById(card_id),
                        LocalDateTime.parse(start_time, Utils.DATE_FORMATER),
                        LocalDateTime.parse(end_time, Utils.DATE_FORMATER),
                        PaymentTransactionManager.getInstance().getTransactionById(rent_transactionid),
                        PaymentTransactionManager.getInstance().getTransactionById(return_transactionid)
                );
                sessions.add(session);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
