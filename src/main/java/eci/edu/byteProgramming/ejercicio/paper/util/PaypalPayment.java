package eci.edu.byteProgramming.ejercicio.paper.util;

public class PaypalPayment extends PaymentMethod {
    private final String email;
    private String paypalTransactionId;

    public PaypalPayment(double amount, String customerId, String description, String email) {
        super(amount, customerId, description);
        this.email = email;
    }

    @Override
    public String getPaymentMethod() {
        return "PAYPAL";
    }

    @Override
    protected boolean authorizePayment() {
        this.paypalTransactionId = "PP" + System.currentTimeMillis();
        System.out.println("PayPal payment authorized for: " + email);
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }
}
