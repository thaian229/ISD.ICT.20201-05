package model.db;


import utils.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Model to manage all connection to database
 *
 * @author mHoang99, Nguyen Thai An
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class EBRDB {

    private static final String url = "jdbc:postgresql://localhost/" + Configs.DB_NAME;
    private static final String user = Configs.DB_USERNAME;
    private static final String password = Configs.DB_PASSWORD;

    private static Connection conn;

    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     * @throws SQLException sql errors
     */
    public static Connection getConnection() throws SQLException {
        if(conn == null || !conn.isValid(2000)) {
            try {
                Class.forName("org.postgresql.Driver");
                Properties props = new Properties();
                props.setProperty("user",Configs.DB_USERNAME);
                props.setProperty("password",Configs.DB_PASSWORD);
                props.setProperty("tcpKeepAlive","true");
                props.setProperty("tcp_keepalives_idle","60");
                props.setProperty("tcp_keepalives_interval","60");

                conn = DriverManager.getConnection(url, props);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            EBRDB.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
