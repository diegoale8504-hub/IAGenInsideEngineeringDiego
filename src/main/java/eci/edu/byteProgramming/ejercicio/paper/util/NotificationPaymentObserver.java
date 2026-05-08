package eci.edu.byteProgramming.ejercicio.paper.util;

public class NotificationPaymentObserver implements PaymentObserver {
    private final Notification notification;

    public NotificationPaymentObserver(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void onPaymentSuccess(PaymentMethod payment, String customerName, String customerEmail, String productId) {
        notification.sendConfirmationEmail(customerEmail, customerName, payment);
    }

    @Override
    public void onPaymentFailed(PaymentMethod payment, String customerEmail) {
        notification.sendFailureNotification(payment, customerEmail);
    }
}
