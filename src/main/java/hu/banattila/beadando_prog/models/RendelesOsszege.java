package hu.banattila.beadando_prog.models;

public class RendelesOsszege {

    private String fajta;
    private int meret;
    private int rendeltMennyiseg;
    private int fizetendo = 0;

    public RendelesOsszege(String fajta, int meret, int rendeltMennyiseg, int fizetendo) {
        this.fajta = fajta;
        this.meret = meret;
        this.rendeltMennyiseg = rendeltMennyiseg;
        this.fizetendo = fizetendo;
    }

    public RendelesOsszege(String fajta, int meret, int rendeltMennyiseg) {
        this.fajta = fajta;
        this.meret = meret;
        this.rendeltMennyiseg = rendeltMennyiseg;
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

    public int getRendeltMennyiseg() {
        return rendeltMennyiseg;
    }

    public void setRendeltMennyiseg(int rendeltMennyiseg) {
        this.rendeltMennyiseg = rendeltMennyiseg;
    }

    public int getFizetendo() {
        return fizetendo;
    }

    public void setFizetendo(int fizetendo) {
        this.fizetendo = fizetendo;
    }
}