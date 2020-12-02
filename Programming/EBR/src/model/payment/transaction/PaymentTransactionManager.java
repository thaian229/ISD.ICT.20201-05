package model.payment.transaction;


import model.db.EBRDB;

import java.sql.*;

public class PaymentTransactionManager {

    private static PaymentTransactionManager instance;

    public static PaymentTransactionManager getInstance() {
        if (instance == null) {
            instance = new PaymentTransactionManager();
        }
        return instance;
    }

    public PaymentTransaction getTransactionById(String transactionId) {
        // query the card
        String SQL = "SELECT * FROM payment_transaction "
                + "WHERE id = ?";

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

    public String savePaymentTransaction(PaymentTransaction paymentTransaction) {
        String SQL = "INSERT INTO payment_transaction(type, amount, method) " +
                "VALUES (?, ?, ?)";

        String id = "save failed";

        // Insert new row
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Set up parameters
            pstmt.setString(1, paymentTransaction.getType());
            pstmt.setInt(2, paymentTransaction.getAmount());
            pstmt.setString(3, paymentTransaction.getMethod());
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

        return id;
    }

}
