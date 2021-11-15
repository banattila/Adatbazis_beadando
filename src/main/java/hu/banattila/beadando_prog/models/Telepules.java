package hu.banattila.beadando_prog.models;

public class Telepules {

    private int irsz;
    private String telepules;

    public Telepules(int irsz, String telepules) {
        this.irsz = irsz;
        this.telepules = telepules;
    }

    public int getIrsz() {
        return irsz;
    }

    public void setIrsz(int irsz) {
        this.irsz = irsz;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }
}