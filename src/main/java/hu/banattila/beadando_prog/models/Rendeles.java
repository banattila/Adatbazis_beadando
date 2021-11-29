package hu.banattila.beadando_prog.models;

import java.time.LocalDateTime;

public class Rendeles {

    private final LocalDateTime rendelesIdeje;
    private final String email;
    private final String fajta;
    private final int meret;
    private final int rendeltMennyiseg;
    private final String cim;
    private final String adoszam;
    private final int fizetendo;

    public Rendeles(LocalDateTime rendelesIdeje, String email, String fajta, int meret,
                    int rendeltMennyiseg, int irsz, String telepules, String utca, int hazszam, String adoszam,
                    int fizetendo) {
        this.rendelesIdeje = rendelesIdeje;
        this.email = email;
        this.fajta = fajta;
        this.meret = meret;
        this.rendeltMennyiseg = rendeltMennyiseg;
        this.cim = irsz + " " + telepules + " " + utca + " " + hazszam;
        this.adoszam = adoszam;
        this.fizetendo = fizetendo;
    }

    public String getRendelesIdeje() {
        String res = rendelesIdeje.toString();
        return res.replace("T", " ");
    }

    public int getFizetendo() {
        return fizetendo;
    }

    public String getEmail() {
        return email;
    }

    public String getFajta() {
        return fajta;
    }

    public String getMeret() {
        String res;
        switch (meret){
            case 26: res = "Kicsi";break;
            case 30: res = "Közepes"; break;
            default: res = "Családi";
        }
        return res;
    }

    public int getRendeltMennyiseg() {
        return rendeltMennyiseg;
    }

    public String getCim() {
        return cim;
    }

    public String getAdoszam() {
        return adoszam;
    }
}