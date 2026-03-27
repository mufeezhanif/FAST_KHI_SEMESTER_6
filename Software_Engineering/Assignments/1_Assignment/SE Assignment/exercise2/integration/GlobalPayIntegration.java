package exercise2.integration;

import exercise2.core.PaymentService;
import exercise2.external.GlobalPayService;

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

