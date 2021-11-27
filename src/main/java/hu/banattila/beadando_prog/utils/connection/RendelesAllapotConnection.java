package hu.banattila.beadando_prog.utils.connection;

import hu.banattila.beadando_prog.models.RendelesAllapot;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RendelesAllapotConnection extends PizzeriaConnection {

    public List<RendelesAllapot> getAllapotok() {
        List<RendelesAllapot> res = new ArrayList<>();
        connecting();

        try {
            pstmt = conn.prepareStatement("SELECT rendeles_ideje, allapota, email FROM RENDELES_ALLAPOTA NATURAL JOIN RENDELES");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res.add(new RendelesAllapot(rs.getString("email"),
                        LocalDateTime.parse(rs.getString("rendeles_ideje"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), rs.getBoolean("allapota"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }

    public String setTeljesitett(String rendelesIdeje) {
        String res = "Ok";
        connecting();

        try {

            //update rendeles allapota
            pstmt = conn.prepareStatement("UPDATE RENDELES_ALLAPOTA SET allapota = true WHERE rendeles_ideje = ?");
            pstmt.setObject(1, rendelesIdeje);
            pstmt.execute();

            //update futar
            pstmt = conn.prepareStatement("UPDATE FUTAR SET elerheto = true WHERE adoszam = (SELECT adoszam FROM RENDELES WHERE rendeles_ideje = ?)");
            pstmt.setObject(1, rendelesIdeje);
            pstmt.execute();


        } catch (SQLException e) {
            res = e.getMessage();
            e.printStackTrace();
        }

        closeConnection();
        return res;
    }
}
