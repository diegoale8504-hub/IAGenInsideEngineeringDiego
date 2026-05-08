package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class RentalServiceAcceptanceTest {
    private final RentalService rentalService = new RentalService();
    private final ReceiptFormatter receiptFormatter = new ReceiptFormatter();

    @Test
    void premiumCustomerRentsPhysicalAndDigitalMoviesWithTwentyPercentDiscount() {
        RentalReceipt receipt = rentalService.rent(new PremiumMembership(), List.of(1, 3));

        assertEquals("Premium", receipt.getMembershipName());
        assertEquals(2, receipt.getMovies().size());
        assertEquals(13000, receipt.getSubtotal());
        assertEquals(2600, receipt.getDiscount());
        assertEquals(10400, receipt.getTotal());

        String formattedReceipt = receiptFormatter.format(receipt);
        assertTrue(formattedReceipt.contains("Cliente: Premium"));
        assertTrue(formattedReceipt.contains(" - Interestellar (Fisica) - $8.000"));
        assertTrue(formattedReceipt.contains(" - Inception (Digital) - $5.000"));
        assertTrue(formattedReceipt.contains("Subtotal: $13.000"));
        assertTrue(formattedReceipt.contains("Descuento (20%): $2.600"));
        assertTrue(formattedReceipt.contains("Total a pagar: $10.400"));
    }

    @Test
    void basicCustomerPaysFullPriceAndUnavailableMoviesAreNotRented() {
        RentalReceipt receipt = rentalService.rent(new BasicMembership(), List.of(1, 2, 4));

        assertEquals("Basica", receipt.getMembershipName());
        assertEquals(2, receipt.getMovies().size());
        assertEquals(14000, receipt.getSubtotal());
        assertEquals(0, receipt.getDiscount());
        assertEquals(14000, receipt.getTotal());

        List<String> rentedTitles = receipt.getMovies().stream().map(Movie::getTitle).toList();
        assertTrue(rentedTitles.contains("Interestellar"));
        assertTrue(rentedTitles.contains("Matrix"));
        assertFalse(rentedTitles.contains("El Padrino"));
    }

    @Test
    void invalidSelectionsAreIgnoredWithoutAffectingTheReceipt() {
        RentalReceipt receipt = rentalService.rent(new PremiumMembership(), List.of(0, 9, 3));

        assertEquals(1, receipt.getMovies().size());
        assertEquals("Inception", receipt.getMovies().get(0).getTitle());
        assertEquals(5000, receipt.getSubtotal());
        assertEquals(1000, receipt.getDiscount());
        assertEquals(4000, receipt.getTotal());
    }
}
