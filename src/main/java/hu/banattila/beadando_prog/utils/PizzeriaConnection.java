package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class PizzeriaConnection {

    private final String URL = "jdbc:mysql://localhost:3306/PIZZERIA";
    private final String USER = "root";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;


    public PizzeriaConnection() {
    }

    private void connecting() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, "");
        } catch (ClassNotFoundException e) {
            System.out.println("Not found driver");
        } catch (SQLException e) {
            System.out.println("Sql error: " + e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUgyfelByEmail(String email) {
        String res = null;

        try {
            pstmt = conn.prepareStatement("SELECT email FROM UGYFEL WHERE email = ? ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                res = rs.getString("email");
            }
        } catch (SQLException e) {
            res = e.getMessage();
        }

        return res;
    }

    public List<Ugyfel> getUgyfelek() {
        connecting();
        List<Ugyfel> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM UGYFEL");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Ugyfel(rs.getString("email"), rs.getString("vezeteknev"), rs.getString("keresztnev")));
            }

        } catch (SQLException e) {
            System.out.println("Not connected");
        }
        closeConnection();
        return result;
    }

    public String insertUgyfel(String email, String vnev, String knev) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("INSERT INTO UGYFEL VALUES(?, ?, ?)");

            pstmt.setString(1, email);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String updateUgyfel(String email, String vnev, String knev) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("UPDATE UGYFEL SET vezeteknev = ?, keresztnev = ? WHERE email = ?");
            pstmt.setString(1, vnev);
            pstmt.setString(2, knev);
            pstmt.setString(3, email);
            pstmt.execute();
        } catch (Exception e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String deleteUgyfel(String email) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("DELETE FROM UGYFEL WHERE email = ?");
            pstmt.setString(1, email);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public List<Dolgozo> getDolgozok() {
        connecting();
        List<Dolgozo> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM DOLGOZO WHERE adoszam NOT IN (SELECT adoszam FROM FUTAR)");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Dolgozo(rs.getString("adoszam"), rs.getString("vezeteknev"), rs.getString("keresztnev")));
            }

        } catch (SQLException e) {
            System.out.println("Not connected");
        }
        closeConnection();
        return result;
    }

    public String insertDolgozo(String adoszam, String vnev, String knev) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("INSERT INTO DOLGOZO VALUES(?, ?, ?)");

            pstmt.setString(1, adoszam);
            pstmt.setString(2, vnev);
            pstmt.setString(3, knev);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String updateDolgozo(String adoszam, String vnev, String knev) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("UPDATE DOLGOZO SET vezeteknev = ?, keresztnev = ? WHERE adoszam = ?");
            pstmt.setString(1, vnev);
            pstmt.setString(2, knev);
            pstmt.setString(3, adoszam);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String deleteDolgozo(String adoszam) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public List<Futar> getFutarok() {
        connecting();
        List<Futar> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM FUTAR INNER JOIN DOLGOZO ON FUTAR.adoszam = DOLGOZO.adoszam");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Futar(rs.getString("adoszam"), rs.getString("vezeteknev"), rs.getString("keresztnev"), rs.getBoolean("elerheto")));
            }

        } catch (SQLException e) {
            System.out.println("Not connected");
        }
        closeConnection();
        return result;
    }

    public String insertFutarok(String adoszam, String vnev, String knev) {
        connecting();
        String res = "Ok";
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
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String deleteFutarok(String adoszam) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("DELETE FROM FUTAR WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
            pstmt = conn.prepareStatement("DELETE FROM DOLGOZO WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String updateFutar(String adoszam) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("SELECT elerheto FROM FUTAR WHERE adoszam = ?");
            pstmt.setString(1, adoszam);
            rs = pstmt.executeQuery();
            boolean elerheto = false;
            while (rs.next()) {
                elerheto = rs.getBoolean("elerheto");
            }

            pstmt = conn.prepareStatement("UPDATE FUTAR SET elerheto = ? WHERE adoszam = ?");
            pstmt.setBoolean(1, !elerheto);
            pstmt.setString(2, adoszam);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public List<Feltet> getFeltetek() {
        connecting();
        List<Feltet> res = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM FELTET");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                res.add(new Feltet(rs.getString("megnevezes"), rs.getInt("ar")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public String insertFeltet(String megnevezes, int ar) {
        connecting();
        String res = "Ok";
        try {
            pstmt = conn.prepareStatement("INSERT INTO FELTET VALUES(?, ?)");
            pstmt.setString(1, megnevezes);
            pstmt.setInt(2, ar);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String deleteFeltet(String megnevezes) {
        String res = "Ok";
        connecting();
        try {
            pstmt = conn.prepareStatement("DELETE FROM FELTET WHERE megnevezes = ?");
            pstmt.setString(1, megnevezes);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public Feltet searchFeltetByMegnevezes(String megnevezes) {
        Feltet res = null;
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM FELTET WHERE megnevezes = ? LIMIT 1");
            pstmt.setString(1, megnevezes);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                res = new Feltet(rs.getString("megnevezes"), rs.getInt("ar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public String updateFeltetArByMegnevezes(String megnevezes, int ar) {
        String res = "Ok";
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = ? WHERE megnevezes = ?");
            pstmt.setInt(1, ar);
            pstmt.setString(2, megnevezes);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }

    public String incFeltetAr() {
        String res = "Ok";
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = (ar * 1.1)");
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }

    public String decFeltetAr() {
        String res = "Ok";
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE FELTET SET ar = (ar * 0.9)");
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }

    public List<Pizza> getPizzak(int meret) {
        List<Pizza> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM PIZZA NATURAL JOIN PIZZA_ALAP WHERE meret = ?");
            pstmt.setInt(1, meret);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res.add(new Pizza(rs.getString("fajta"), rs.getInt("meret"), rs.getInt("ar"), rs.getString("pizza_alapja")));
            }
            for (Pizza p : res) {
                pstmt = conn.prepareStatement("SELECT megnevezes FROM TARTALMAZ WHERE fajta = ?");
                pstmt.setString(1, p.getFajta());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    p.addOsszetevo(rs.getString("megnevezes"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public List<String> pizzaFajtak(){
        List<String> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT DISTINCT fajta FROM PIZZA");
            rs = pstmt.executeQuery();

            while (rs.next()){
                res.add(rs.getString("fajta"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public List<String> getAlapok() {
        List<String> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT DISTINCT pizza_alapja FROM PIZZA_ALAP");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                res.add(rs.getString("pizza_alapja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public String addPizzak(String fajta, List<String> osszetevok, String alap, int ar, int meret) {
        String res = "Ok";
        connecting();
        try {
            //insert pizza
            pstmt = conn.prepareStatement("INSERT INTO PIZZA VALUES(?, ?, ?)");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, meret);
            pstmt.setInt(3, ar);
            pstmt.execute();

            //insert alap
            pstmt = conn.prepareStatement("INSERT INTO PIZZA_ALAP VALUES(?, ?)");
            pstmt.setString(1, fajta);
            pstmt.setString(2, alap);
            pstmt.execute();

            //insert TARTALMAZ

            for (String osszetevo : osszetevok) {
                pstmt = conn.prepareStatement("INSERT INTO TARTALMAZ VALUES(?, ?)");
                pstmt.setString(1, fajta);
                pstmt.setString(2, osszetevo);
                pstmt.execute();
            }

        } catch (SQLException e) {
            res = e.getMessage();
        }
        closeConnection();
        return res;
    }

    public String updatePizza(String fajta, int ar, int meret) {
        String res = "Ok";
        connecting();

        try {
            pstmt = conn.prepareStatement("UPDATE PIZZA SET ar = ? WHERE fajta = ? AND meret = ?");
            pstmt.setInt(1, ar);
            pstmt.setString(2, fajta);
            pstmt.setInt(3, meret);
            pstmt.execute();
        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }

    public String deletePizza(String fajta, int meret) {
        String res = "Ok";
        connecting();

        try {
            //delete from alap
            pstmt = conn.prepareStatement("DELETE FROM PIZZA_ALAP WHERE fajta = ?");
            pstmt.setString(1, fajta);
            pstmt.execute();

            //delete from tartalmaz
            pstmt = conn.prepareStatement("DELETE FROM TARTALMAZ WHERE fajta = ?");
            pstmt.setString(1, fajta);
            pstmt.execute();

            //delete from pizza
            pstmt = conn.prepareStatement("DELETE FROM PIZZA WHERE fajta = ? AND meret = ?");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, meret);
            pstmt.execute();

        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }

    public List<Rendeles> getRendelesek() {
        List<Rendeles> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM RENDELES NATURAL JOIN RENDELES_OSSZEGE NATURAL JOIN TELEPULES");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res.add(new Rendeles(
                        LocalDateTime.parse(rs.getString("rendeles_ideje"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        rs.getString("email"),
                        rs.getString("fajta"),
                        rs.getInt("meret"),
                        rs.getInt("rendelt_mennyiseg"),
                        rs.getInt("irsz"),
                        rs.getString("telepules"),
                        rs.getString("utca"),
                        rs.getInt("hazszam"),
                        rs.getString("adoszam"),
                        rs.getInt("fizetendo")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public String insertRendeles(String email, String vnev, String knev, String fajta, int meret,
                                 int mennyiseg, int irsz, String telepules, String utca,
                                 int hazszam){
        String res = "Ok";
        connecting();

        try {

            //check available futar
            pstmt = conn.prepareStatement("SELECT adoszam FROM FUTAR WHERE elerheto = 1");
            rs = pstmt.executeQuery();

            String elerhetoFutarok  = "";
            while (rs.next()){
                elerhetoFutarok = rs.getString("adoszam");
            }

            if (elerhetoFutarok.isEmpty()){
                return "Nincs szabad futár, aki elvihetné a fuvart";
            }

            //insert ugyfel if not exists
            pstmt = conn.prepareStatement("SELECT email FROM UGYFEL WHERE email = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            String emailInTable = "";
            while (rs.next()){
                emailInTable = rs.getString("email");
            }
            if (emailInTable.isEmpty()){
                pstmt = conn.prepareStatement("INSERT INTO UGYFEL VALUES (?, ?, ?)");
                pstmt.setString(1, email);
                pstmt.setString(2, vnev);
                pstmt.setString(3, knev);
                pstmt.execute();
            }

            //insert telepules if not exists
            pstmt = conn.prepareStatement("SELECT irsz FROM TELEPULES WHERE irsz = ?");
            pstmt.setInt(1, irsz);
            rs = pstmt.executeQuery();
            int irszInTable = -1;
            while (rs.next()){
                irszInTable = rs.getInt("irsz");
            }

            if (irszInTable == -1){
                pstmt = conn.prepareStatement("INSERT INTO TELEPULES VALUES(?, ?)");
                pstmt.setInt(1, irsz);
                pstmt.setString(2, telepules);
                pstmt.execute();
            }

            pstmt = conn.prepareStatement("INSERT INTO RENDELES_OSSZEGE VALUES(?, ?, ?, (SELECT ar FROM PIZZA WHERE fajta = ? AND MERET = ?) * ?)");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, meret);
            pstmt.setInt(3, mennyiseg);
            pstmt.setString(4, fajta);
            pstmt.setInt(5, meret);
            pstmt.setInt(6, mennyiseg);
            pstmt.execute();

            pstmt = conn.prepareStatement("INSERT INTO RENDELES VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setObject(1, LocalDateTime.now());
            pstmt.setString(2, email);
            pstmt.setString(3, fajta);
            pstmt.setInt(4, meret);
            pstmt.setInt(5, mennyiseg);
            pstmt.setInt(6, irsz);
            pstmt.setString(7, utca);
            pstmt.setInt(8, hazszam);
            pstmt.setString(9, elerhetoFutarok);
            pstmt.execute();

            //update futar elerheto
            pstmt = conn.prepareStatement("UPDATE FUTAR SET elerheto = ? WHERE adoszam = ?");
            pstmt.setBoolean(1, false);
            pstmt.setString(2, elerhetoFutarok);
            pstmt.execute();


        } catch (SQLException e){
            res = e.getMessage();
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }
}
