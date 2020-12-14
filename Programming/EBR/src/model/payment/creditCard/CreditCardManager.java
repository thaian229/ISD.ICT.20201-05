package model.payment.creditCard;


import model.db.EBRDB;

import java.sql.*;

/**
 * Model to manage all credit card and handle database access
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
public class CreditCardManager {

    private static CreditCardManager instance; // singleton

    /**
     * singleton instance access
     *
     * @return CreditCardManager instance
     */
    public static CreditCardManager getInstance() {
        if (instance == null) {
            instance = new CreditCardManager();
        }
        return instance;
    }

    /**
     * query card info from database via id
     *
     * @param cardId card's uuid
     * @return instance of wanted card, null if didn't found
     */
    public CreditCard getCardById(String cardId) {
        // query the card
        String SQL = "SELECT * FROM card " +
                "WHERE id = ?::uuid";

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

    public CreditCard getCardByCardNumber(String cardNumber) {
        // query the card
        String SQL = "SELECT * FROM card " +
                "WHERE card_num = ?";

        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // Set up parameters
            pstmt.setString(1, cardNumber);
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

    /**
     * save the card into database
     *
     * @param creditCard instance of credit card to be saved
     * @return uuid of the card in the newly created records
     */
    public String saveCreditCard(CreditCard creditCard) {
        String SQL = "INSERT INTO card(card_num, card_owner, security_code, exp_date) " +
                "VALUES (?, ?, ?, ?)";

        String id = "save failed";

        CreditCard checkExisted = this.getCardByCardNumber(creditCard.getCardNum());
        if (checkExisted != null) {
            creditCard.setId(checkExisted.getId());
            return checkExisted.getId();
        }

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
        creditCard.setId(id);
        return id;
    }

}
