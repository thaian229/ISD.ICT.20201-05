package model.payment.transaction;


import model.db.EBRDB;

import java.sql.*;

/**
 * Model to manage all payment transaction and handle database access
 *
 * @author Vu Minh Hoang, Nguyen Thai An
 * <p>
 * creted at: 24/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class PaymentTransactionManager {

    private static PaymentTransactionManager instance;  // singleton

    /**
     * singleton instance access
     *
     * @return PaymentTransactionManager instance
     */
    public static PaymentTransactionManager getInstance() {
        if (instance == null) {
            instance = new PaymentTransactionManager();
        }
        return instance;
    }

    /**
     * query Payment Transaction info from database via id
     *
     * @param transactionId transaction's uuid
     * @return instance of wanted transaction, null if didn't found
     */
    public PaymentTransaction getTransactionById(String transactionId) {
        // query the card
        String SQL = "SELECT * FROM payment_transaction "
                + "WHERE id = ?::uuid";

        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // Set up parameters
            pstmt.setString(1, transactionId);
            // Handle result set
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            return new PaymentTransaction(
                    rs.getString("id"),
                    rs.getString("type"),
                    rs.getInt("amount"),
                    rs.getString("method")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * save transaction into database as new record
     *
     * @param paymentTransaction transaction to be saved
     * @return uuid of newly added transaction's record
     */
    public String savePaymentTransaction(PaymentTransaction paymentTransaction) {
        String SQL = "INSERT INTO payment_transaction(type, amount, method, card_id) " +
                "VALUES (?, ?, ?, ?::uuid)";

        String id = "save failed";

        // Insert new row
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Set up parameters
            pstmt.setString(1, paymentTransaction.getType());
            pstmt.setInt(2, paymentTransaction.getAmount());
            pstmt.setString(3, paymentTransaction.getMethod());
            pstmt.setString(4, paymentTransaction.getCard().getId());
            // Handle update
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the id back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString("id");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        paymentTransaction.setId(id);
        return id;
    }

}
