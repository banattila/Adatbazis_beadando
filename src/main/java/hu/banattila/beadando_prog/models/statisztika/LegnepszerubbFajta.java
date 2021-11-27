package hu.banattila.beadando_prog.models.statisztika;

public class LegnepszerubbFajta {

    private final String fajta;
    private final int darab;
    private final int meret;

    public LegnepszerubbFajta(String fajta, int darab, int meret) {
        this.fajta = fajta;
        this.darab = darab;
        this.meret = meret;
    }

    public String getFajta() {
        return fajta;
    }

    public int getDarab() {
        return darab;
    }

    public int getMeret() {
        return meret;
    }
}
