package base.integration;

import base.core.PaymentService;
import base.external.LegacyPayService;

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

