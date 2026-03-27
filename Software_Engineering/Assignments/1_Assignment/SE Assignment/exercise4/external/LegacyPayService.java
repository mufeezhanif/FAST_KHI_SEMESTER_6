package exercise4.external;

public class LegacyPayService {

    public boolean makePayment(double valueInDollars) {
        System.out.println("[LegacyPayService] Processing payment of $" + valueInDollars);
        return true;
    }
}

