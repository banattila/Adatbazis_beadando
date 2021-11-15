package hu.banattila.beadando_prog.models;

public class Ugyfel {
    private String email;
    private String vezeteknev;
    private String keresztnev;

    public Ugyfel(String email, String vezeteknev, String keresztnev) {
        this.email = email;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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