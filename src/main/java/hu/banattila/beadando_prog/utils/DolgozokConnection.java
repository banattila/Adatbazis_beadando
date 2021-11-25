package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Futar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DolgozokConnection extends PizzeriaConnection {

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
}
