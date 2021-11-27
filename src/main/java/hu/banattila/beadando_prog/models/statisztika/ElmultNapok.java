package hu.banattila.beadando_prog.models.statisztika;

import java.sql.Date;

public class ElmultNapok {

    private final String fajta;
    private final Date date;
    private final int mennyiseg;

    public ElmultNapok(String fajta, Date date, int mennyiseg) {
        this.fajta = fajta;
        this.date = date;
        this.mennyiseg = mennyiseg;
    }

    public String getFajta() {
        return fajta;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public String getDate() {
        String res = date.toString();
        return res.replace("T", "");
    }
}
