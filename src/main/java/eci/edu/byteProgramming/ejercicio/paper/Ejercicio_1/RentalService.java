package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private final List<Movie> inventory;

    public RentalService() {
        this(defaultInventory());
    }

    public RentalService(List<Movie> inventory) {
        this.inventory = List.copyOf(inventory);
    }

    public List<Movie> getInventory() {
        return inventory;
    }

    public RentalReceipt rent(MembershipStrategy membership, List<Integer> selectedMovieNumbers) {
        List<Movie> selectedMovies = new ArrayList<>();
        int subtotal = 0;

        for (Integer movieNumber : selectedMovieNumbers) {
            if (movieNumber == null || movieNumber < 1 || movieNumber > inventory.size()) {
                continue;
            }

            Movie movie = inventory.get(movieNumber - 1);
            if (movie.isAvailable()) {
                selectedMovies.add(movie);
                subtotal += movie.getPrice();
            }
        }

        int discount = membership.calculateDiscount(subtotal);
        return new RentalReceipt(
                membership.getName(),
                membership.getDiscountPercentage(),
                selectedMovies,
                subtotal,
                discount);
    }

    private static List<Movie> defaultInventory() {
        return List.of(
                new PhysicalMovie("Interestellar", 8000, true),
                new PhysicalMovie("El Padrino", 7000, false),
                new DigitalMovie("Inception", 5000, true),
                new DigitalMovie("Matrix", 6000, true));
    }
}
