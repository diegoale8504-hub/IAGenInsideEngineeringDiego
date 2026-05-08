package eci.edu.byteProgramming.ejercicio.paper.util;

public class CryptoValidator implements ValidatePayment {
    private final String walletAddress;
    private final double walletBalance;
    private final double amount;

    public CryptoValidator(String walletAddress, double walletBalance, double amount) {
        this.walletAddress = walletAddress;
        this.walletBalance = walletBalance;
        this.amount = amount;
    }

    @Override
    public boolean validatePaymentMethod() {
        return validateWalletAddress() && validateBalance();
    }

    private boolean validateWalletAddress() {
        return walletAddress != null && walletAddress.length() >= 26;
    }

    private boolean validateBalance() {
        return walletBalance >= amount;
    }
}
