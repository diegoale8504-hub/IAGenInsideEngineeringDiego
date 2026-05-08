package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

public class DigitalMovie extends Movie {
    public DigitalMovie(String title, int price, boolean available) {
        super(title, price, available);
    }

    @Override
    public String getType() {
        return "Digital";
    }
}
