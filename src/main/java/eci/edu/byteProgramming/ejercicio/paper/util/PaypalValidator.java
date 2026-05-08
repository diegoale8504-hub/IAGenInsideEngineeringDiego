package eci.edu.byteProgramming.ejercicio.paper.util;

public class PaypalValidator implements ValidatePayment {
    private final String email;
    private final String authToken;

    public PaypalValidator(String email, String authToken) {
        this.email = email;
        this.authToken = authToken;
    }

    @Override
    public boolean validatePaymentMethod() {
        return validateEmail() && validateAuthToken();
    }

    private boolean validateEmail() {
        return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean validateAuthToken() {
        return authToken != null && authToken.length() > 10;
    }
}
