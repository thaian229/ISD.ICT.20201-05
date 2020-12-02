package model.payment.creditCard;


import model.db.EBRDB;

import java.sql.*;

public class CreditCardManager {

    private static CreditCardManager instance;

    public static CreditCardManager getInstance() {
        if (instance == null) {
            instance = new CreditCardManager();
        }
        return instance;
    }

    public CreditCard getCardById(String cardId) {
        // query the card
        String SQL = "SELECT * FROM card " +
                "WHERE id = ?";

        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // Set up parameters
            pstmt.setString(1, cardId);
            // Handle result set
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            return new CreditCard(
                    rs.getString("id"),
                    rs.getString("card_num"),
                    rs.getString("card_owner"),
                    Integer.parseInt(rs.getString("security_code")),
                    rs.getString("exp_date")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String saveCreditCard(CreditCard creditCard) {
        String SQL = "INSERT INTO card(card_num, card_owner, security_code, exp_date) " +
                "VALUES (?, ?, ?, ?)";

        String id = "save failed";

        // Insert new row
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Set up parameters
            pstmt.setString(1, creditCard.getCardNum());
            pstmt.setString(2, creditCard.getCardOwner());
            pstmt.setString(3, Integer.toString(creditCard.getSecurityCode()));
            pstmt.setString(4, creditCard.getExpDate());
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
