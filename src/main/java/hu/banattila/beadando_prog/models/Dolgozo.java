package hu.banattila.beadando_prog.models;

public class Dolgozo {

    private final String adoszam;
    private final String vezeteknev;
    private final String keresztnev;

    public Dolgozo(String adoszam, String vezeteknev, String keresztnev) {
        this.adoszam = adoszam;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }
}