package eci.edu.byteProgramming.ejercicio.paper.util;

public class FacturationPaymentObserver implements PaymentObserver {
    private final Facturation facturation;
    private final Inventory inventory;

    public FacturationPaymentObserver(Facturation facturation, Inventory inventory) {
        this.facturation = facturation;
        this.inventory = inventory;
    }

    @Override
    public void onPaymentSuccess(PaymentMethod payment, String customerName, String customerEmail, String productId) {
        Product product = inventory.getProduct(productId);
        String productDetails = product != null ? product.getName() : productId;
        facturation.generateInvoice(payment, customerName, productDetails);
    }

    @Override
    public void onPaymentFailed(PaymentMethod payment, String customerEmail) {
        System.out.println("Facturation: payment failed, invoice was not generated");
    }
}
