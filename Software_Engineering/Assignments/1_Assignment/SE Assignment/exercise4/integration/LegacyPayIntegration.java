package exercise4.integration;

import exercise4.core.PaymentService;
import exercise4.external.LegacyPayService;

public class LegacyPayIntegration implements PaymentService {

    private final LegacyPayService legacyPayService;

    public LegacyPayIntegration(LegacyPayService legacyPayService) {
        this.legacyPayService = legacyPayService;
    }

    @Override
    public boolean pay(double amount) {
        return legacyPayService.makePayment(amount);
    }
}

