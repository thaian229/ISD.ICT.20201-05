package model.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EBRDB {

    private static final String url = "jdbc:postgresql://localhost:3333/ebr";
    private static final String user = "postgres";
    private static final String password = "Bim12345%";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EBRDB.getConnection();
    }

}
