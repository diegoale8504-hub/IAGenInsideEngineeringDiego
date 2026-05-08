package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

public interface MembershipStrategy {
    int calculateDiscount(int subtotal);

    String getName();

    int getDiscountPercentage();
}
