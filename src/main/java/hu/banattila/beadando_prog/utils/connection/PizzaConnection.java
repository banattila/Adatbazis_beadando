package hu.banattila.beadando_prog.utils.connection;

import hu.banattila.beadando_prog.models.Pizza;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaConnection extends PizzeriaConnection {

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

    public List<String> pizzaFajtak() {
        List<String> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT DISTINCT fajta FROM PIZZA");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res.add(rs.getString("fajta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public String addPizzak(String fajta, List<String> osszetevok, String alap, int ar) {
        String res = "Ok";
        connecting();
        try {
            //insert pizza minden méretben
            //kicsi
            pstmt = conn.prepareStatement("INSERT INTO PIZZA VALUES(?, ?, ?)");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, 26);
            pstmt.setInt(3, ar);
            pstmt.execute();
            //kozepes
            pstmt = conn.prepareStatement("INSERT INTO PIZZA VALUES(?, ?, ?)");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, 30);
            pstmt.setInt(3, (int) (ar * 1.2));
            pstmt.execute();
            //nagy
            pstmt = conn.prepareStatement("INSERT INTO PIZZA VALUES(?, ?, ?)");
            pstmt.setString(1, fajta);
            pstmt.setInt(2, 50);
            pstmt.setInt(3, (int) (ar * 3.2));
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

    public String deletePizza(String fajta) {
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
            pstmt = conn.prepareStatement("DELETE FROM PIZZA WHERE fajta = ?");
            pstmt.setString(1, fajta);
            pstmt.execute();

        } catch (SQLException e) {
            res = e.getMessage();
        }

        closeConnection();
        return res;
    }
}
