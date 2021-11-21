package hu.banattila.beadando_prog.models;

public class Pizza {

    private String fajta;
    private int meret = 24;
    private int ar;
    private String alap;
    private String osszetevok = "";

    public Pizza(String fajta, int ar) {
        this.fajta = fajta;
        this.ar = ar;
    }

    public Pizza(String fajta, int meret, int ar, String alap) {
        this.fajta = fajta;
        this.meret = meret;
        this.ar = ar;
        this.alap = alap;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }

    public int getMeret() {
        return meret;
    }

    public void setMeret(int meret) {
        this.meret = meret;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getAlap() {
        return alap;
    }

    public String getOsszetevok() {
        return osszetevok;
    }

    public void addOsszetevo(String osszetevo) {
        if (osszetevok.isEmpty()) {
            osszetevok += osszetevo;
        } else {
            osszetevok += ", " + osszetevo;
        }
    }
}