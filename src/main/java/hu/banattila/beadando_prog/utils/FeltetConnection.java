package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.models.Feltet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeltetConnection extends PizzeriaConnection {

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
}
