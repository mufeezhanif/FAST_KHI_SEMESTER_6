package exercise3.client;

import exercise3.core.PaymentService;
import exercise3.external.ConfirmPayService;
import exercise3.integration.StandardLogIntegration;

public class Main {

    public static void main(String[] args) {
        PaymentService service =
                new StandardLogIntegration(new ConfirmPayService());
        PaymentClient client = new PaymentClient(service);

        client.processPayment(300.0);
    }
}

