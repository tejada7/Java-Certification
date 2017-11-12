package dessignPatterns.behavioral.chainOfResponsibility;

/**
 * Created by Favio on 12/11/2017.
 */
public class Request {

    private RequestType requestType;
    private double amount;

    public Request(RequestType requestType, double amount) {
        this.requestType = requestType;
        this.amount = amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public double getAmount() {
        return amount;
    }
}
