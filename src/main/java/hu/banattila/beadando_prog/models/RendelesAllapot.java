package hu.banattila.beadando_prog.models;

import java.time.LocalDateTime;

public class RendelesAllapot {

    private LocalDateTime rendelesIdeje;
    private boolean allapota;

    public RendelesAllapot(LocalDateTime rendelesIdeje, boolean allapota) {
        this.rendelesIdeje = rendelesIdeje;
        this.allapota = allapota;
    }

    public LocalDateTime getRendelesIdeje() {
        return rendelesIdeje;
    }

    public void setRendelesIdeje(LocalDateTime rendelesIdeje) {
        this.rendelesIdeje = rendelesIdeje;
    }

    public boolean isAllapota() {
        return allapota;
    }

    public void setAllapota(boolean allapota) {
        this.allapota = allapota;
    }
}