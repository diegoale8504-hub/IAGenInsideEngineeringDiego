package eci.edu.byteProgramming.ejercicio.paper.util;

import java.util.Date;

public abstract class PaymentMethod {
    protected double amount;
    protected String transactionID;
    protected String customerID;
    protected String currency;
    protected Date timestamp;
    protected PaymentStatus status;
    protected String description;

    public PaymentMethod(double amount, String customerID, String description) {
        this.amount = amount;
        this.customerID = customerID;
        this.description = description;
        this.currency = "USD";
        this.status = PaymentStatus.PENDING;
        this.timestamp = new Date();
        this.transactionID = generateTransactionIdWithPrefix(getPaymentMethod());
    }

    public abstract String getPaymentMethod();

    public boolean processPayment(ValidatePayment validator) {
        System.out.println("Processing " + getPaymentMethod() + " payment...");

        if (!validator.validatePaymentMethod()) {
            System.out.println(getPaymentMethod() + " validation failed!");
            setStatus(PaymentStatus.FAILED);
            return false;
        }

        setStatus(PaymentStatus.PROCESSING);

        if (authorizePayment()) {
            setStatus(PaymentStatus.COMPLETED);
            return true;
        }

        setStatus(PaymentStatus.FAILED);
        return false;
    }

    protected abstract boolean authorizePayment();

    protected String generateTransactionId() {
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 9999);
        return String.format("TXN%d%04d", timestamp, random);
    }

    protected String generateTransactionIdWithPrefix(String paymentType) {
        String prefix = getPaymentTypePrefix(paymentType);
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 9999);
        return String.format("%s%d%04d", prefix, timestamp, random);
    }

    private String getPaymentTypePrefix(String paymentType) {
        return switch (paymentType) {
            case "CREDIT_CARD" -> "CC";
            case "PAYPAL" -> "PP";
            case "CRYPTOCURRENCY" -> "CR";
            default -> "TX";
        };
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() { return amount; }
    public String getTransactionId() { return transactionID; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public String getCustomerId() { return customerID; }
    public String getDescription() { return description; }
    public Date getTimestamp() { return timestamp; }
}
