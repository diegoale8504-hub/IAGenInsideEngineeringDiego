package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

public class BasicMembership implements MembershipStrategy {
    @Override
    public int calculateDiscount(int subtotal) {
        return 0;
    }

    @Override
    public String getName() {
        return "Basica";
    }

    @Override
    public int getDiscountPercentage() {
        return 0;
    }
}
