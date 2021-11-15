package hu.banattila.beadando_prog.models;

public class Tartalmaz{

    private String megnevezes;
    private String fajta;

    public Tartalmaz(String megnevezes, String fajta) {
        this.megnevezes = megnevezes;
        this.fajta = fajta;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }
}