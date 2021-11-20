package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Feltet;
import hu.banattila.beadando_prog.models.Futar;
import hu.banattila.beadando_prog.models.Ugyfel;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class PizzeriaConnection {

    private final String URL = "jdbc:mysql://localhost:3306/PIZZERIA";
    private final String USER = "root";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;


    public PizzeriaConnection(){
    }

    private void connecting(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, "");
        } catch (ClassNotFoundException e){
            System.out.println("Not found driver");
        } catch (SQLException e){
            System.out.println("Sql error: " + e.getMessage());
        }
    }

    private void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ugyfel> getUgyfelek(){
        connecting();
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
        closeConnection();
        return result;
    }

    public boolean insertUgyfel(String email, String vnev, String knev) {
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO UGYFEL VALUES(?, ?, ?)");

            pstmt.setString(1, email);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            pstmt.execute();
        } catch (SQLException e) {
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean updateUgyfel(String email, String vnev, String knev){
        connecting();
        boolean res = true;
        try{
            pstmt = conn.prepareStatement("UPDATE UGYFEL SET vezeteknev = ?, keresztnev = ? WHERE email = ?");
            pstmt.setString(1, vnev);
            pstmt.setString(2, knev);
            pstmt.setString(3, email);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean deleteUgyfel(String email){
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("DELETE FROM UGYFEL WHERE email = ?");
            pstmt.setString(1, email);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public List<Dolgozo> getDolgozok(){
        connecting();
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
        closeConnection();
        return result;
    }

    public boolean insertDolgozo(String adoszam, String vnev, String knev) {
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO DOLGOZO VALUES(?, ?, ?)");

            pstmt.setString(1, adoszam);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            pstmt.execute();
        } catch (SQLException e) {
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean updateDolgozo(String adoszam, String vnev, String knev){
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("UPDATE DOLGOZO SET vezeteknev = ?, keresztnev = ? WHERE adoszam = ?");
            pstmt.setString(1, vnev);
            pstmt.setString(2, knev);
            pstmt.setString(3, adoszam);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean deleteDolgozo(String adoszam){
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public List<Futar> getFutarok(){
        connecting();
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
        closeConnection();
        return result;
    }

    public boolean insertFutarok(String adoszam, String vnev, String knev) {
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO DOLGOZO VALUES(?, ?, ?)");

            pstmt.setString(1, adoszam);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            pstmt.execute();
            pstmt = conn.prepareStatement("INSERT INTO FUTAR VALUES(?, ?)");
            pstmt.setString(1, adoszam);
            pstmt.setBoolean(2, false);
            pstmt.execute();
        } catch (SQLException e) {
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean deleteFutarok(String adoszam){
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("DELETE FROM FUTAR WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean updateFutar(String adoszam){
        connecting();
        boolean res = true;
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
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public List<Feltet> getFeltetek(){
        connecting();
        List<Feltet> res = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM FELTET");
            rs = pstmt.executeQuery();
            while (rs.next()){
                res.add(new Feltet(rs.getString("megnevezes"), rs.getInt("ar")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean insertFeltet(String megnevezes, int ar){
        connecting();
        boolean res = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO FELTET VALUES(?, ?)");
            pstmt.setString(1, megnevezes);
            pstmt.setInt(2, ar);
            pstmt.execute();
        } catch (SQLException e) {
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public boolean deleteFeltet(String megnevezes){
        boolean res = true;
        connecting();
        try {
            pstmt = conn.prepareStatement("DELETE FROM FELTET WHERE megnevezes = ?");
            pstmt.setString(1, megnevezes);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public Feltet searchFeltetByMegnevezes(String megnevezes){
        Feltet res = null;
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM FELTET WHERE megnevezes = ? LIMIT 1");
            pstmt.setString(1, megnevezes);
            rs = pstmt.executeQuery();
            while (rs.next()){
                res = new Feltet(rs.getString("megnevezes"), rs.getInt("ar"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public boolean updateFeltetArByMegnevezes(String megnevezes, int ar){
        boolean res = true;
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = ? WHERE megnevezes = ?");
            pstmt.setInt(1, ar);
            pstmt.setString(2, megnevezes);
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public boolean incFeltetAr(){
        boolean res = true;
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = (ar * 1.1)");
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public boolean decFeltetAr(){
        boolean res = true;
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = (ar * 0.9)");
            pstmt.execute();
        } catch (SQLException e){
            res = false;
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }
}
