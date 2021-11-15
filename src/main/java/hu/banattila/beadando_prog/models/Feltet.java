package hu.banattila.beadando_prog.models;

public class Feltet {

    private String megnevezes;
    private int ar;

    public Feltet(String megnevezes, int ar) {
        this.megnevezes = megnevezes;
        this.ar = ar;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }
}