package hu.banattila.beadando_prog.models;

import java.time.LocalDateTime;

public class RendelesAllapot {
    private final String email;
    private final LocalDateTime rendelesIdeje;
    private final boolean allapota;

    public RendelesAllapot(String email, LocalDateTime rendelesIdeje, boolean allapota) {
        this.email = email;
        this.rendelesIdeje = rendelesIdeje;
        this.allapota = allapota;
    }

    public String getEmail(){
        return email;
    }

    public String getRendelesIdeje() {
        String res = rendelesIdeje.toString();
        return res.replace("T", " ");
    }

    public String isAllapota() {
        return allapota?"Teljesített":"Nem teljesített";
    }

}