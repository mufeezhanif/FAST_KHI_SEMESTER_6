package exercise4.core;

public class SwitchablePaymentService implements PaymentService {

    private PaymentService delegate;

    public void setDelegate(PaymentService delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean pay(double amount) {
        if (delegate == null) {
            throw new IllegalStateException("No payment provider configured");
        }
        return delegate.pay(amount);
    }
}

