package exercise5.integration;

import exercise5.core.PaymentService;
import exercise5.external.MockPayService;

public class MockPayIntegration implements PaymentService {

    private final MockPayService mockPayService;

    public MockPayIntegration(MockPayService mockPayService) {
        this.mockPayService = mockPayService;
    }

    @Override
    public boolean pay(double amount) {
        return mockPayService.simulate(amount);
    }
}

