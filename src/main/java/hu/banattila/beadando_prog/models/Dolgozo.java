package hu.banattila.beadando_prog.models;

public class Dolgozo {

    private String adoszam;
    private String vezeteknev;
    private String keresztnev;

    public Dolgozo(String adoszam, String vezeteknev, String keresztnev) {
        this.adoszam = adoszam;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }

    public void setKeresztnev(String keresztnev) {
        this.keresztnev = keresztnev;
    }
}