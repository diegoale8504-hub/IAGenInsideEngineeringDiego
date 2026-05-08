package eci.edu.byteProgramming.ejercicio.paper.Ejercicio_1;

public class PremiumMembership implements MembershipStrategy {
    @Override
    public int calculateDiscount(int subtotal) {
        return subtotal * 20 / 100;
    }

    @Override
    public String getName() {
        return "Premium";
    }

    @Override
    public int getDiscountPercentage() {
        return 20;
    }
}
