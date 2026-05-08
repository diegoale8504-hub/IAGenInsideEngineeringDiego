package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

import java.util.Collections;
import java.util.List;

public class RentalReceipt {
    private final String membershipName;
    private final int discountPercentage;
    private final List<Movie> movies;
    private final int subtotal;
    private final int discount;
    private final int total;

    public RentalReceipt(String membershipName, int discountPercentage, List<Movie> movies, int subtotal, int discount) {
        this.membershipName = membershipName;
        this.discountPercentage = discountPercentage;
        this.movies = List.copyOf(movies);
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = subtotal - discount;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getDiscount() {
        return discount;
    }

    public int getTotal() {
        return total;
    }
}
