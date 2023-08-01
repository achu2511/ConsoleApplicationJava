package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHandler {
     static public String dbURL = "jdbc:mysql://localhost:3306/CarRental";
     static public String username = "root";
     static public String password = "Achyuth@25";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, username, password);
//            System.out.println("Databse connected Successfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
