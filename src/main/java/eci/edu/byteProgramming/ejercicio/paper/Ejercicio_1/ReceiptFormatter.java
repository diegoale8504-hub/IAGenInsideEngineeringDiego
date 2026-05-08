package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ReceiptFormatter {
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale.US));

    public String format(RentalReceipt receipt) {
        StringBuilder builder = new StringBuilder();
        builder.append("--- RECIBO DE ALQUILER ---").append(System.lineSeparator());
        builder.append("Cliente: ").append(receipt.getMembershipName()).append(System.lineSeparator());
        builder.append("Peliculas:").append(System.lineSeparator());

        for (Movie movie : receipt.getMovies()) {
            builder.append(" - ")
                    .append(movie.getTitle())
                    .append(" (")
                    .append(movie.getType())
                    .append(") - $")
                    .append(formatCurrency(movie.getPrice()))
                    .append(System.lineSeparator());
        }

        builder.append("Subtotal: $").append(formatCurrency(receipt.getSubtotal())).append(System.lineSeparator());
        builder.append("Descuento (")
                .append(receipt.getDiscountPercentage())
                .append("%): $")
                .append(formatCurrency(receipt.getDiscount()))
                .append(System.lineSeparator());
        builder.append("Total a pagar: $").append(formatCurrency(receipt.getTotal())).append(System.lineSeparator());
        builder.append("--------------------------").append(System.lineSeparator());
        builder.append("Disfrute su pelicula!");
        return builder.toString();
    }

    private String formatCurrency(int amount) {
        return CURRENCY_FORMAT.format(amount).replace(',', '.');
    }
}
