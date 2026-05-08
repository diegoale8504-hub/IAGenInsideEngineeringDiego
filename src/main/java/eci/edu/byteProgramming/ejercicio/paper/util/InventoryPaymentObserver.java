package eci.edu.byteProgramming.ejercicio.paper.util;

public class InventoryPaymentObserver implements PaymentObserver {
    private final Inventory inventory;

    public InventoryPaymentObserver(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void onPaymentSuccess(PaymentMethod payment, String customerName, String customerEmail, String productId) {
        inventory.discountProduct(productId, 1);
    }

    @Override
    public void onPaymentFailed(PaymentMethod payment, String customerEmail) {
        System.out.println("Inventory: payment failed, stock remains unchanged");
    }
}
