package exercise5.client;

import exercise5.core.PaymentService;
import exercise5.external.MockPayService;
import exercise5.integration.MockPayIntegration;

public class Main {

    public static void main(String[] args) {
        PaymentService service =
                new MockPayIntegration(new MockPayService());
        PaymentClient client = new PaymentClient(service);

        client.processPayment(500.0);
    }
}

