package exercise3.integration;

import exercise3.core.PaymentService;
import exercise3.external.ConfirmPayService;

public class StandardLogIntegration implements PaymentService {

    private final ConfirmPayService confirmPayService;

    public StandardLogIntegration(ConfirmPayService confirmPayService) {
        this.confirmPayService = confirmPayService;
    }

    @Override
    public boolean pay(double amount) {
        String confirmationCode = confirmPayService.confirmPayment(amount);
        System.out.println("[AlphaSoft Log] Payment successful -> " + confirmationCode
                           + " :: Amount=" + amount);
        return "TXN-OK".equals(confirmationCode);
    }
}

