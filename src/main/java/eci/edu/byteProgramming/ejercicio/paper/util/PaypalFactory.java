package eci.edu.byteProgramming.ejercicio.paper.util;

public class PaypalFactory implements PaymentFactory {
    private final String email;
    private final String authToken;

    public PaypalFactory(String email, String authToken) {
        this.email = email;
        this.authToken = authToken;
    }

    @Override
    public PaymentMethod createPaymentMethod(double amount, String customerId, String description) {
        return new PaypalPayment(amount, customerId, description, email);
    }

    @Override
    public ValidatePayment createValidator(PaymentMethod paymentMethod) {
        return new PaypalValidator(email, authToken);
    }
}
