package exercise4.client;

import exercise4.core.SwitchablePaymentService;
import exercise4.core.PaymentService;
import exercise4.external.LegacyPayService;
import exercise4.external.GlobalPayService;
import exercise4.integration.LegacyPayIntegration;
import exercise4.integration.GlobalPayIntegration;

public class Main {

    public static void main(String[] args) {
        SwitchablePaymentService switchable = new SwitchablePaymentService();

        PaymentService legacyAdapter =
                new LegacyPayIntegration(new LegacyPayService());
        PaymentService globalAdapter =
                new GlobalPayIntegration(new GlobalPayService(), "EUR");

        switchable.setDelegate(legacyAdapter);

        PaymentClient client = new PaymentClient(switchable);
        client.processPayment(120.0);

        System.out.println("[System] Payment provider switched.");

        switchable.setDelegate(globalAdapter);
        client.processPayment(120.0);
    }
}

