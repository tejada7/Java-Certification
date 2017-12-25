package dessignPatterns.behavioral.template;

public abstract class OrderTemplate {

    public boolean isGift;
    public abstract void doCheckot();
    public abstract void doPayment();
    public abstract void doReceipt();
    public abstract void doDelivery();

    public final void wrapGift() {
        System.out.println("Gift was wrapped.");
    }

    /**
     * Algorithm to be behave always similarly among inherited classes.
     */
    public final void processOrder() {
        doCheckot();
        doPayment();
        if (isGift) {
            wrapGift();
        } else {
            doReceipt();
        }
        doDelivery();
    }
}
