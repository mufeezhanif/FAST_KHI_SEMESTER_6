package exercise2.client;

import exercise2.core.PaymentService;
import exercise2.external.GlobalPayService;
import exercise2.integration.GlobalPayIntegration;

public class Main {

    public static void main(String[] args) {
        PaymentService service =
                new GlobalPayIntegration(new GlobalPayService(), "USD");
        PaymentClient client = new PaymentClient(service);

        client.processPayment(150.75);
    }
}

