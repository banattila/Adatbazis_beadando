package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Futar;
import hu.banattila.beadando_prog.models.Ugyfel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzeriaConnection {

    private final String URL = "jdbc:mysql://localhost:3306/PIZZERIA";
    private final String USER = "root";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;


    public PizzeriaConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, "");
        } catch (ClassNotFoundException e){
            System.out.println("Not found driver");
        } catch (SQLException e){
            System.out.println("Sql error: " + e.getMessage());
        }
    }

    public List<Ugyfel> getUgyfelek(){
        List<Ugyfel> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM UGYFEL");
            rs = pstmt.executeQuery();
            while (rs.next()){
                result.add(new Ugyfel(rs.getString("email"), rs.getString("vezeteknev"), rs.getString("keresztnev")));
            }

        } catch (SQLException e){
            System.out.println("Not connected");
        }
        return result;
    }

    public boolean insertUgyfel(String email, String vnev, String knev) {
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("INSERT INTO UGYFEL VALUES(?, ?, ?)");

            pstmt.setString(1, email);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            res = pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteUgyfel(String email){
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("DELETE FROM UGYFEL WHERE email = ?");
            pstmt.setString(1, email);
            res = pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public List<Dolgozo> getDolgozok(){
        List<Dolgozo> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM DOLGOZO WHERE adoszam NOT IN (SELECT adoszam FROM FUTAR)");
            rs = pstmt.executeQuery();
            while (rs.next()){
                result.add(new Dolgozo(rs.getString("adoszam"), rs.getString("vezeteknev"), rs.getString("keresztnev")));
            }

        } catch (SQLException e){
            System.out.println("Not connected");
        }
        return result;
    }

    public boolean insertDolgozo(String adoszam, String vnev, String knev) {
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("INSERT INTO DOLGOZO VALUES(?, ?, ?)");

            pstmt.setString(1, adoszam);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            res = pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteDolgozo(String adoszam){
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            res = pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public List<Futar> getFutarok(){
        List<Futar> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM FUTAR INNER JOIN DOLGOZO ON FUTAR.adoszam = DOLGOZO.adoszam");
            rs = pstmt.executeQuery();
            while (rs.next()){
                result.add(new Futar(rs.getString("adoszam"), rs.getString("vezeteknev"), rs.getString("keresztnev"), rs.getBoolean("elerheto")));
            }

        } catch (SQLException e){
            System.out.println("Not connected");
        }
        return result;
    }

    public boolean insertFutarok(String adoszam, String vnev, String knev) {
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("INSERT INTO DOLGOZO VALUES(?, ?, ?)");

            pstmt.setString(1, adoszam);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            res = pstmt.execute();
            pstmt = conn.prepareStatement("INSERT INTO FUTAR VALUES(?, ?)");
            pstmt.setString(1, adoszam);
            pstmt.setBoolean(2, false);
            res = pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteFutarok(String adoszam){
        boolean res = false;
        try {
            pstmt = conn.prepareStatement("DELETE FROM FUTAR WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            res = pstmt.execute();
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adozsam = ?");
            pstmt.setString(1, adoszam);
            res = pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateFutar(String adoszam){
        boolean res = false;

        try {
            pstmt = conn.prepareStatement("SELECT elerheto FROM FUTAR WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            rs = pstmt.executeQuery();
            boolean elerheto = false;
            while (rs.next()){
                elerheto = rs.getBoolean("elerheto");
            }

            pstmt = conn.prepareStatement("UPDATE FUTAR SET elerheto = ? WHERE adoszam = ?");
            pstmt.setBoolean(1, !elerheto);
            pstmt.setString(2, adoszam);
            res = pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
