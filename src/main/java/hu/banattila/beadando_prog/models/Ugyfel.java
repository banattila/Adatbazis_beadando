package hu.banattila.beadando_prog.models;

public class Ugyfel {
    private final String email;
    private final String vezeteknev;
    private final String keresztnev;

    public Ugyfel(String email, String vezeteknev, String keresztnev) {
        this.email = email;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
    }

    public String getEmail() {
        return email;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }
}