package hu.banattila.beadando_prog.utils.connection;

import hu.banattila.beadando_prog.models.statisztika.ElmultNapok;
import hu.banattila.beadando_prog.models.statisztika.LegnepszerubbFajta;
import hu.banattila.beadando_prog.models.statisztika.LegugyesebbFutar;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisztikaConnection extends PizzeriaConnection {

    public List<ElmultNapok> getElmultNapok(int hetekSzama) {
        List<ElmultNapok> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT fajta, date(rendeles_ideje) AS datum, COUNT(date(rendeles_ideje)) * rendelt_mennyiseg AS darab " +
                    "FROM RENDELES" +
                    " WHERE rendeles_ideje >= subdate(now(), interval ? week ) " +
                    "GROUP BY fajta, date(rendeles_ideje) " +
                    "ORDER BY date(rendeles_ideje)");
            pstmt.setInt(1, hetekSzama);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res.add(new ElmultNapok(
                        rs.getString("fajta"),
                        Date.valueOf(rs.getString("datum")),
                        rs.getInt("darab")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public LegnepszerubbFajta getLegnepszerubb() {
        LegnepszerubbFajta res = null;
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT fajta, meret, SUM(rendelt_mennyiseg) AS darab " +
                    "FROM RENDELES" +
                    " GROUP BY fajta, meret " +
                    "HAVING darab = (" +
                    "SELECT SUM(rendelt_mennyiseg) AS db " +
                    "FROM RENDELES " +
                    "GROUP BY fajta, meret " +
                    "ORDER BY db DESC " +
                    "LIMIT 1" +
                    ");");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = new LegnepszerubbFajta(rs.getString("fajta"), rs.getInt("darab"), rs.getInt("meret"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }

    public LegugyesebbFutar legtobbetDolgozott() {
        LegugyesebbFutar res = null;
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT CONCAT(vezeteknev, ' ', keresztnev) AS nev, COUNT(rendeles_ideje) AS munkaja FROM DOLGOZO " +
                    "INNER JOIN FUTAR ON DOLGOZO.adoszam = FUTAR.adoszam " +
                    "INNER JOIN RENDELES ON FUTAR.adoszam = RENDELES.adoszam " +
                    "GROUP BY RENDELES.adoszam " +
                    "HAVING munkaja = (" +
                    "SELECT COUNT(rendeles_ideje) FROM RENDELES " +
                    "GROUP BY adoszam " +
                    "ORDER BY COUNT(rendeles_ideje) DESC " +
                    "LIMIT 1);");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = new LegugyesebbFutar(rs.getString("nev"), rs.getInt("munkaja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return res;
    }
}
