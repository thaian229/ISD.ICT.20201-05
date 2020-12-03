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
        String sessionId = this.insertNewSessions(newSession);
        newSession.setId(sessionId);
        refreshSessionsList();
        return newSession;
    }

    public int endSession(Session session, PaymentTransaction returnTransaction) {
        session.setEndTime(LocalDateTime.now());
        session.setReturnTransaction(returnTransaction);

        String SQL = "UPDATE session "
                + "SET (end_time, return_transactionid) = (?, ?) "
                + "WHERE id = ? ";

        int affectedrows = 0;

        try (
                PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL);
        ) {
            pstmt.setString(1, session.getEndTime().format(Utils.DATE_FORMATER));
            pstmt.setString(2, returnTransaction.getId());
            pstmt.setString(3, session.getId());

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedrows;
    }

    public Session getSessionById(String id) {
        for (Session session : sessions) {
            if (session.getId().equals(id))
                return session;
        }
        return null;
    }

    private String insertNewSessions(Session newSession) {
        String SQL = "INSERT INTO session(bike_id, card_id, rent_transactionid, start_time) "
                + "VALUES(?,?,?,?)";
        String id = "";

        // Insert new row
        try (
                PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL);
        ) {
            // Set up parameters
            pstmt.setString(1, newSession.getBike().getId());
            pstmt.setString(2, newSession.getCard().getId());
            pstmt.setString(3, newSession.getRentTransaction().getId());
            pstmt.setString(4, newSession.getStartTime().format(Utils.DATE_FORMATER));
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

    private void refreshSessionsList() {
        sessions.clear();
        String SQL = "SELECT * FROM session";

        // Get All Rows
        try (
                Statement stmt = EBRDB.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(SQL)
        ) {
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
            ex.printStackTrace();
        }

    }
}
