package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.Rendeles;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RendelesekConnection extends PizzeriaConnection {

    public List<Rendeles> getRendelesek() {
        List<Rendeles> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM RENDELES NATURAL JOIN RENDELES_OSSZEGE NATURAL JOIN TELEPULES ORDER BY rendeles_ideje DESC ");
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
                                 int hazszam) {
        String res = "Ok";
        connecting();

        try {

            //check available futar
            pstmt = conn.prepareStatement("SELECT adoszam FROM FUTAR WHERE elerheto = 1");
            rs = pstmt.executeQuery();

            String elerhetoFutarok = "";
            while (rs.next()) {
                elerhetoFutarok = rs.getString("adoszam");
            }

            if (elerhetoFutarok.isEmpty()) {
                return "Nincs szabad futár, aki elvihetné a fuvart";
            }

            //insert ugyfel if not exists
            pstmt = conn.prepareStatement("SELECT email FROM UGYFEL WHERE email = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            String emailInTable = "";
            while (rs.next()) {
                emailInTable = rs.getString("email");
            }
            if (emailInTable.isEmpty()) {
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
            while (rs.next()) {
                irszInTable = rs.getInt("irsz");
            }

            if (irszInTable == -1) {
                pstmt = conn.prepareStatement("INSERT INTO TELEPULES VALUES(?, ?)");
                pstmt.setInt(1, irsz);
                pstmt.setString(2, telepules);
                pstmt.execute();
            }

            pstmt = conn.prepareStatement("SELECT fizetendo FROM RENDELES_OSSZEGE WHERE fajta = ? AND meret = ? AND rendelt_mennyiseg = ?");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, meret);
            pstmt.setInt(3, mennyiseg);
            rs = pstmt.executeQuery();
            int osszegRes = -1;
            while (rs.next()) {
                osszegRes = rs.getInt("fizetendo");
            }
            if (osszegRes == -1) {
                pstmt = conn.prepareStatement("INSERT INTO RENDELES_OSSZEGE VALUES(?, ?, ?, (SELECT ar FROM PIZZA WHERE fajta = ? AND MERET = ?) * ?)");
                pstmt.setString(1, fajta);
                pstmt.setInt(2, meret);
                pstmt.setInt(3, mennyiseg);
                pstmt.setString(4, fajta);
                pstmt.setInt(5, meret);
                pstmt.setInt(6, mennyiseg);
                pstmt.execute();
            }

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


        } catch (SQLException e) {
            res = e.getMessage();
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }
}
