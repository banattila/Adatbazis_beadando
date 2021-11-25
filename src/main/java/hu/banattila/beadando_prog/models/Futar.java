package hu.banattila.beadando_prog.models;

public class Futar extends Dolgozo {

    private String elerheto = "Nem";

    public Futar(String adoszam, String vnev, String knev, boolean elerheto) {
        super(adoszam, vnev, knev);
        setElerheto(elerheto);
    }

    public String getElerheto() {
        return elerheto;
    }

    public void setElerheto(boolean elerheto) {
        if (elerheto) {
            this.elerheto = "Igen";
        } else {
            this.elerheto = "Nem";
        }
    }
}