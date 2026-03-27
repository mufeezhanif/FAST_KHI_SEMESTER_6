package exercise1.external;

public class CentPayService {

    public boolean processInCents(int amountInCents) {
        System.out.println("[CentPayService] Processing payment of " + amountInCents + " cents");
        return true;
    }
}

