package hu.banattila.beadando_prog.models;

public class Feltet {

    private final String megnevezes;
    private final int ar;

    public Feltet(String megnevezes, int ar) {
        this.megnevezes = megnevezes;
        this.ar = ar;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public int getAr() {
        return ar;
    }
}