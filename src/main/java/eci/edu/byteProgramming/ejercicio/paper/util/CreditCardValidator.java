package eci.edu.byteProgramming.ejercicio.paper.util;

public class CreditCardValidator implements ValidatePayment {
    private final String number;
    private final String expirationDate;
    private final String cvv;

    public CreditCardValidator(String number, String expirationDate, String cvv) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validatePaymentMethod() {
        return validateCardNumber() && validateCVV() && validateExpirationDate();
    }

    private boolean validateCardNumber() {
        return number != null && number.matches("\\d{13,19}");
    }

    private boolean validateCVV() {
        return cvv != null && cvv.matches("\\d{3,4}");
    }

    private boolean validateExpirationDate() {
        return expirationDate != null && expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}");
    }
}
