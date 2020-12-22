package model.session;

import model.bike.Bike;
import model.bike.BikeManager;
import model.db.EBRDB;
import model.payment.creditCard.CreditCard;
import model.payment.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * class for accessing DB related functions and managing list of Session
 *
 * @author mHoang
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

public class SessionManager {

    private static SessionManager instance; //singleton
    private final ArrayList<Session> sessions = new ArrayList<>();

    /**
     * @author mHoang
     * This constructor will refresh the session list when the object is initialized
     */
    private SessionManager() {
        this.refreshSessionsList();
    }

    /**
     * This is the SessionManager provider
     *
     * @return instance SessionManager object
     * @author mHoang
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * This method is for create new Session object and update it details to the DB
     *
     * @param bike            Bike
     * @param card            Card
     * @param rentTransaction PaymentTransaction
     * @return newSession new Session object
     * @author mHoang
     */
    public Session createSession(Bike bike, CreditCard card, PaymentTransaction rentTransaction) {
        Session newSession = new Session(bike, card, rentTransaction);
        newSession.setActive(true);
        this.insertNewSessions(newSession);
//        refreshSessionsList();
        sessions.add(newSession);
        return newSession;
    }

    /**
     * This method is to end the session and update in DB
     *
     * @param session           session to be ended
     * @param returnTransaction transaction to refund deposit after deducting rental fee
     * @return affectedRows number of affected rows in DB
     * @author mHoang
     */
    public int endSession(Session session, PaymentTransaction returnTransaction) {
        session.setEndTime(LocalDateTime.now());
        session.setReturnTransaction(returnTransaction);

        String SQL = "UPDATE session "
                + "SET (end_time, return_transactionid) = (?, ?::uuid) "
                + "WHERE id = ?::uuid ";

        int affectedRows = 0;

        try (PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, session.getEndTime().format(Utils.DATE_FORMATER));
            pstmt.setString(2, session.getReturnTransaction().getId());
            pstmt.setString(3, session.getId());

            affectedRows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    /**
     * get session object by passing an id
     *
     * @param id session's uuid
     * @return session Session object
     * @author mHoang
     */
    public Session getSessionById(String id) {
        for (Session session : sessions) {
            if (session.getId().equals(id))
                return session;
        }
        return null;
    }

    public ArrayList<Session> getSessions() {
//        this.refreshSessionsList();
        return sessions;
    }

    /**
     * for inserting new session to DB
     *
     * @param newSession instance of session to be inserted
     * @return id new record id
     * @author mHoang
     */
    private String insertNewSessions(Session newSession) {
        String SQL = "INSERT INTO session(bike_id, card_id, rent_transactionid, start_time) "
                + "VALUES(?::uuid,?::uuid,?::uuid,?)";
        String id = "";

        // Insert new row
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
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
                        id = rs.getString("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        newSession.setId(id);
        return id;
    }

    /**
     * @author mHoang
     * get all sessions from DB and store them to the list
     */
    private void refreshSessionsList() {
        sessions.clear();
        String SQL = "SELECT * FROM session";

        // Get All Rows
        try (Statement stmt = EBRDB.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String bike_id = rs.getString("bike_id");
                String card_id = rs.getString("card_id");
                String rent_transactionid = rs.getString("rent_transactionid");
                String return_transactionid = rs.getString("return_transactionid");
                String start_time = rs.getString("start_time");
                String end_time = rs.getString("end_time");
                int last_rent_time_before_lock = rs.getInt("last_rent_time_before_lock");
                String last_resume_time = rs.getString("last_resume_time");
                boolean active = rs.getBoolean("active");
                Session session;

                if (return_transactionid == null || end_time == null) {
                    session = new Session(
                            id,
                            BikeManager.getInstance().getBikeById(bike_id),
                            CreditCardManager.getInstance().getCardById(card_id),
                            LocalDateTime.parse(start_time, Utils.DATE_FORMATER),
                            PaymentTransactionManager.getInstance().getTransactionById(rent_transactionid)
                    );
                } else {
                    session = new Session(
                            id,
                            BikeManager.getInstance().getBikeById(bike_id),
                            CreditCardManager.getInstance().getCardById(card_id),
                            LocalDateTime.parse(start_time, Utils.DATE_FORMATER),
                            LocalDateTime.parse(end_time, Utils.DATE_FORMATER),
                            PaymentTransactionManager.getInstance().getTransactionById(rent_transactionid),
                            PaymentTransactionManager.getInstance().getTransactionById(return_transactionid)
                    );
                }
                session.setActive(active);
                if (last_resume_time == null)
                    session.setLastResumeTime(LocalDateTime.parse(start_time, Utils.DATE_FORMATER));
                else
                    session.setLastResumeTime(LocalDateTime.parse(last_resume_time, Utils.DATE_FORMATER));
                session.setLastRentTimeBeforeLock(last_rent_time_before_lock);
                sessions.add(session);
            }
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
        }

    }


    private int pauseSession(Session session) {
        int realRentingTime = (int) (session.getLastRentTimeBeforeLock() + Utils.minusLocalDateTime(session.getLastResumeTime(), LocalDateTime.now()));
        session.setLastRentTimeBeforeLock(realRentingTime);
        session.setActive(false);

        String SQL = "UPDATE session "
                + "SET (last_rent_time_before_lock, active) = (?, ?) "
                + "WHERE id = ?::uuid ";

        int affectedRows = 0;

        try (PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, realRentingTime);
            pstmt.setBoolean(2, false);
            pstmt.setString(3, session.getId());

            affectedRows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    private int resumeSession(Session session) {
        session.setLastResumeTime(LocalDateTime.now());
        session.setActive(true);

        String SQL = "UPDATE session "
                + "SET (active, last_resume_time) = (?, ?) "
                + "WHERE id = ?::uuid ";

        int affectedRows = 0;

        try (PreparedStatement pstmt = EBRDB.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setBoolean(1, true);
            pstmt.setString(2, session.getLastResumeTime().format(Utils.DATE_FORMATER));
            pstmt.setString(3, session.getId());

            affectedRows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public void switchSessionState(Session session) {
        if (session.isActive()) {
            pauseSession(session);
        } else {
            resumeSession(session);
        }
    }
}
