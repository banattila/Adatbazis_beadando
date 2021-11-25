package hu.banattila.beadando_prog.models;

import java.time.LocalDateTime;

public class Rendeles {

    private LocalDateTime rendelesIdeje;
    private String email;
    private String fajta;
    private int meret;
    private int rendeltMennyiseg;
    private String telepules;
    private String cim;
    private String adoszam;
    private int fizetendo;


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

    public int getMeret() {
        return meret;
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