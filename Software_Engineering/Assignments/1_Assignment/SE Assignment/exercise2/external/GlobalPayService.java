package exercise2.external;

public class GlobalPayService {

    public boolean executeTransaction(String currency, double value) {
        System.out.println("[GlobalPayService] Processing " + value + " " + currency);
        return true;
    }
}

