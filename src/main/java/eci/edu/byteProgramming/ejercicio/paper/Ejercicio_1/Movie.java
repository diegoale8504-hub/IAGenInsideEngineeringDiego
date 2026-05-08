package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

public abstract class Movie {
    private final String title;
    private final int price;
    private final boolean available;

    protected Movie(String title, int price, boolean available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public abstract String getType();
}
