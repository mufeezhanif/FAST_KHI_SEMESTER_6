package exercise5.external;

public class MockPayService {

    public boolean simulate(double amount) {
        System.out.println("[MockPayService] Simulating payment of $" + amount);
        System.out.println("[Test Mode] No real transaction executed.");
        return true;
    }
}

