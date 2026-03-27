package base.client;

import base.core.PaymentService;
import base.external.LegacyPayService;
import base.integration.LegacyPayIntegration;

public class Main {

    public static void main(String[] args) {
        PaymentService service = new LegacyPayIntegration(new LegacyPayService());
        PaymentClient client = new PaymentClient(service);

        client.processPayment(250.0);
    }
}

