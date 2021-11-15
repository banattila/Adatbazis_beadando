package hu.banattila.beadando_prog.models;

public class Futar {

    private String adoszam;
    private boolean elerheto = false;

    public Futar(String adoszam){
        this.adoszam = adoszam;
    }

    public Futar(String adoszam, boolean elerheto) {
        this.adoszam = adoszam;
        this.elerheto = elerheto;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }

    public boolean isElerheto() {
        return elerheto;
    }

    public void setElerheto(boolean elerheto) {
        this.elerheto = elerheto;
    }
}