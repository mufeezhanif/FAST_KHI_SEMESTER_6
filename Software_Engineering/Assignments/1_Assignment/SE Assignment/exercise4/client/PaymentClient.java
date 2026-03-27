package exercise4.client;

import exercise4.core.PaymentService;

public class PaymentClient {

    private final PaymentService paymentService;

    public PaymentClient(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processPayment(double amount) {
        System.out.println("Client initiating payment...");
        boolean success = paymentService.pay(amount);
        if (success) {
            System.out.println("Payment completed successfully.");
        } else {
            System.out.println("Payment failed.");
        }
    }
}

