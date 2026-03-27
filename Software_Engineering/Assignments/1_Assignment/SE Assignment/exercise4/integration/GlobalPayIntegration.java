package exercise4.integration;

import exercise4.core.PaymentService;
import exercise4.external.GlobalPayService;

public class GlobalPayIntegration implements PaymentService {

    private final GlobalPayService globalPayService;
    private final String currencyCode;

    public GlobalPayIntegration(GlobalPayService globalPayService, String currencyCode) {
        this.globalPayService = globalPayService;
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean pay(double amount) {
        return globalPayService.executeTransaction(currencyCode, amount);
    }
}

