package hu.banattila.beadando_prog.models.statisztika;

public class LegtobbetFizetett {

    private final String nev;
    private final int osszeg;

    public LegtobbetFizetett(String nev,  int osszeg){
        this.nev = nev;
        this.osszeg = osszeg;
    }

    public String getNev() {
        return nev;
    }

    public int getOsszeg() {
        return osszeg;
    }
}
