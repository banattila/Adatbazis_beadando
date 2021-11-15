package hu.banattila.beadando_prog.models;

import java.time.LocalDateTime;

public class Rendeles {

    private LocalDateTime rendelesIdeje;
    private String email;
    private String fajta;
    private int meret;
    private int rendeltMennyiseg;
    private long cimId;
    private String adoszam;


    public Rendeles(LocalDateTime rendelesIdeje, String email, String fajta, int meret, int rendeltMennyiseg, long cimId, String adoszam) {
        this.rendelesIdeje = rendelesIdeje;
        this.email = email;
        this.fajta = fajta;
        this.meret = meret;
        this.rendeltMennyiseg = rendeltMennyiseg;
        this.cimId = cimId;
        this.adoszam = adoszam;
    }

    public LocalDateTime getRendelesIdeje() {
        return rendelesIdeje;
    }

    public void setRendelesIdeje(LocalDateTime rendelesIdeje) {
        this.rendelesIdeje = rendelesIdeje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getCimId() {
        return cimId;
    }

    public void setCimId(long cimId) {
        this.cimId = cimId;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }
}