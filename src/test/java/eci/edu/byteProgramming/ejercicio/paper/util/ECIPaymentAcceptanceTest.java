package eci.edu.byteProgramming.ejercicio.paper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class ECIPaymentAcceptanceTest {

    @Test
    void successfulCreditCardPaymentNotifiesInventoryFacturationAndNotificationModules() {
        Inventory inventory = new Inventory();
        Facturation facturation = new Facturation();
        Notification notification = new Notification();
        ECIPayment eciPayment = createPaymentCore(inventory, facturation, notification);

        boolean success = eciPayment.processPayment(
                new CreditCardFactory("4111111111111111", "Ana Gomez", "12/30", "123", "Calle 1"),
                800.00,
                "CUSTOMER-1",
                "Smartphone purchase",
                "Ana Gomez",
                "ana@example.com",
                "PHONE001");

        assertTrue(success);
        assertEquals(9, inventory.getStock("PHONE001"));
        assertEquals("PHONE001", inventory.getLastDiscountedProductId());
        assertEquals("INV-1001", facturation.getLastGeneratedInvoiceNumber());
        assertEquals(1, notification.getSentConfirmationEmails());
        assertEquals(0, notification.getSentFailureNotifications());
        assertEquals("ana@example.com", notification.getLastRecipient());
    }

    @Test
    void failedPaypalPaymentDoesNotDiscountStockOrGenerateInvoiceAndSendsFailureNotification() {
        Inventory inventory = new Inventory();
        Facturation facturation = new Facturation();
        Notification notification = new Notification();
        ECIPayment eciPayment = createPaymentCore(inventory, facturation, notification);

        boolean success = eciPayment.processPayment(
                new PaypalFactory("invalid-email", "short"),
                45.99,
                "CUSTOMER-2",
                "Book purchase",
                "Luis Perez",
                "luis@example.com",
                "BOOK001");

        assertFalse(success);
        assertEquals(20, inventory.getStock("BOOK001"));
        assertNull(inventory.getLastDiscountedProductId());
        assertNull(facturation.getLastGeneratedInvoiceNumber());
        assertEquals(0, notification.getSentConfirmationEmails());
        assertEquals(1, notification.getSentFailureNotifications());
        assertEquals("luis@example.com", notification.getLastRecipient());
    }

    @Test
    void newObserversCanReactToSuccessfulPaymentsWithoutChangingThePaymentCore() {
        Inventory inventory = new Inventory();
        Facturation facturation = new Facturation();
        Notification notification = new Notification();
        ECIPayment eciPayment = createPaymentCore(inventory, facturation, notification);
        AuditObserver auditObserver = new AuditObserver();
        eciPayment.addObserver(auditObserver);

        boolean success = eciPayment.processPayment(
                new CryptoFactory("12345678901234567890123456", "ETH", 2_000.00),
                1_200.00,
                "CUSTOMER-3",
                "Gaming laptop purchase",
                "Maria Lopez",
                "maria@example.com",
                "LAPTOP001");

        assertTrue(success);
        assertEquals(1, auditObserver.successEvents);
        assertEquals(0, auditObserver.failureEvents);
        assertEquals("CRYPTOCURRENCY", auditObserver.lastPaymentMethod);
    }

    private ECIPayment createPaymentCore(Inventory inventory, Facturation facturation, Notification notification) {
        ECIPayment eciPayment = new ECIPayment();
        eciPayment.addObserver(new InventoryPaymentObserver(inventory));
        eciPayment.addObserver(new FacturationPaymentObserver(facturation, inventory));
        eciPayment.addObserver(new NotificationPaymentObserver(notification));
        return eciPayment;
    }

    private static class AuditObserver implements PaymentObserver {
        private int successEvents;
        private int failureEvents;
        private String lastPaymentMethod;

        @Override
        public void onPaymentSuccess(PaymentMethod payment, String customerName, String customerEmail, String productId) {
            successEvents++;
            lastPaymentMethod = payment.getPaymentMethod();
        }

        @Override
        public void onPaymentFailed(PaymentMethod payment, String customerEmail) {
            failureEvents++;
            lastPaymentMethod = payment.getPaymentMethod();
        }
    }
}
