package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Peliculas Disponibles");
        List<Movie> inventory = rentalService.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            Movie movie = inventory.get(i);
            String availability = movie.isAvailable() ? "Disponible" : "No disponible";
            System.out.printf("%d. [%s] %s - $%s - %s%n",
                    i + 1,
                    movie.getType(),
                    movie.getTitle(),
                    formatCurrency(movie.getPrice()),
                    availability);
        }

        System.out.print("Membresia del cliente (1. Basica, 2. Premium): ");
        MembershipStrategy membership = scanner.nextLine().trim().equals("2")
                ? new PremiumMembership()
                : new BasicMembership();

        System.out.print("Seleccione peliculas (numeros separados por coma): ");
        List<Integer> selectedMovies = parseMovieNumbers(scanner.nextLine());
        RentalReceipt receipt = rentalService.rent(membership, selectedMovies);

        System.out.println(new ReceiptFormatter().format(receipt));
    }

    private static List<Integer> parseMovieNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(Main::parseOrZero)
                .toList();
    }

    private static int parseOrZero(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    private static String formatCurrency(int amount) {
        return String.format("%,d", amount).replace(',', '.');
    }
}
