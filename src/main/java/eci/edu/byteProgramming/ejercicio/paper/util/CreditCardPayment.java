package eci.edu.byteProgramming.ejercicio.paper.util;

public class CreditCardPayment extends PaymentMethod {
    private final String number;
    private final String cardHolderName;
    private final String address;

    public CreditCardPayment(double amount, String customerId, String description,
                             String number, String cardHolderName, String address) {
        super(amount, customerId, description);
        this.number = number;
        this.cardHolderName = cardHolderName;
        this.address = address;
    }

    @Override
    public String getPaymentMethod() {
        return "CREDIT_CARD";
    }

    @Override
    protected boolean authorizePayment() {
        System.out.println("Contacting bank for card: " + maskCardNumber());
        System.out.println("Payment authorized by bank");
        return true;
    }

    public String maskCardNumber() {
        return "**** **** **** " + number.substring(number.length() - 4);
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getAddress() {
        return address;
    }
}
