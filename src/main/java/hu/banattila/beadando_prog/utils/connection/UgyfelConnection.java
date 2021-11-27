package hu.banattila.beadando_prog.utils.connection;

import hu.banattila.beadando_prog.models.Ugyfel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UgyfelConnection extends PizzeriaConnection {

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
}
