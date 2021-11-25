package hu.banattila.beadando_prog.utils;

import java.sql.*;

public class PizzeriaConnection {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;


    public PizzeriaConnection() {
    }

    protected void connecting() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String USER = "root";
            String URL = "jdbc:mysql://localhost:3306/PIZZERIA";
            conn = DriverManager.getConnection(URL, USER, "");
        } catch (ClassNotFoundException e) {
            System.out.println("Not found driver");
        } catch (SQLException e) {
            System.out.println("Sql error: " + e.getMessage());
        }
    }

    protected void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
