package exercise1.integration;

import exercise1.core.PaymentService;
import exercise1.external.CentPayService;

public class CentPayIntegration implements PaymentService {

    private final CentPayService centPayService;

    public CentPayIntegration(CentPayService centPayService) {
        this.centPayService = centPayService;
    }

    @Override
    public boolean pay(double amount) {
        int cents = (int) Math.round(amount * 100.0);
        return centPayService.processInCents(cents);
    }
}

