package hu.banattila.beadando_prog.models;

public class Cim {

    private long cimId;
    private int irsz;
    private String utca;
    private int hazszam;
    private int emelet = 0;
    private int ajto = 0;

    public Cim(long cimId, int irsz, String utca, int hazszam, int emelet, int ajto) {
        this.cimId = cimId;
        this.irsz = irsz;
        this.utca = utca;
        this.hazszam = hazszam;
        this.emelet = emelet;
        this.ajto = ajto;
    }

    public Cim(long cimId, int irsz, String utca, int hazszam) {
        this.cimId = cimId;
        this.irsz = irsz;
        this.utca = utca;
        this.hazszam = hazszam;
    }

    public long getCimId() {
        return cimId;
    }

    public void setCimId(long cimId) {
        this.cimId = cimId;
    }

    public int getIrsz() {
        return irsz;
    }

    public void setIrsz(int irsz) {
        this.irsz = irsz;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public int getHazszam() {
        return hazszam;
    }

    public void setHazszam(int hazszam) {
        this.hazszam = hazszam;
    }

    public int getEmelet() {
        return emelet;
    }

    public void setEmelet(int emelet) {
        this.emelet = emelet;
    }

    public int getAjto() {
        return ajto;
    }

    public void setAjto(int ajto) {
        this.ajto = ajto;
    }
}