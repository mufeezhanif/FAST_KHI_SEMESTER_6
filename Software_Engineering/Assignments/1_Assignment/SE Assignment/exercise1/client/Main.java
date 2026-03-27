package exercise1.client;

import exercise1.core.PaymentService;
import exercise1.external.CentPayService;
import exercise1.integration.CentPayIntegration;

public class Main {

    public static void main(String[] args) {
        PaymentService service = new CentPayIntegration(new CentPayService());
        PaymentClient client = new PaymentClient(service);

        client.processPayment(99.99);
    }
}

