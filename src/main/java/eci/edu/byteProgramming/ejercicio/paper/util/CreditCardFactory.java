package eci.edu.byteProgramming.ejercicio.paper.util;

public class CreditCardFactory implements PaymentFactory {
    private final String number;
    private final String name;
    private final String expirationDate;
    private final String cvv;
    private final String address;

    public CreditCardFactory(String number, String name, String expirationDate, String cvv, String address) {
        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.address = address;
    }

    @Override
    public PaymentMethod createPaymentMethod(double amount, String customerId, String description) {
        return new CreditCardPayment(amount, customerId, description, number, name, address);
    }

    @Override
    public ValidatePayment createValidator(PaymentMethod paymentMethod) {
        return new CreditCardValidator(number, expirationDate, cvv);
    }
}
