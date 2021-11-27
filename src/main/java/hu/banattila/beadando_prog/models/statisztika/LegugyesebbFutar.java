package hu.banattila.beadando_prog.models.statisztika;

public class LegugyesebbFutar {

    private final String nev;
    private final int fuvarokSzama;

    public LegugyesebbFutar(String nev, int fuvarokSzama) {
        this.fuvarokSzama = fuvarokSzama;
        this.nev = nev;
    }

    public int getFuvarokSzama() {
        return fuvarokSzama;
    }

    public String getNev() {
        return nev;
    }
}
