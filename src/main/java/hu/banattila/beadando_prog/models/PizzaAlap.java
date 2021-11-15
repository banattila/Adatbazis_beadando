package hu.banattila.beadando_prog.models;

public class PizzaAlap{

    private String fajta;
    private String pizzaAlapja;

    public PizzaAlap(String fajta, String pizzaAlapja) {
        this.fajta = fajta;
        this.pizzaAlapja = pizzaAlapja;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }

    public String getPizzaAlapja() {
        return pizzaAlapja;
    }

    public void setPizzaAlapja(String pizzaAlapja) {
        this.pizzaAlapja = pizzaAlapja;
    }
}