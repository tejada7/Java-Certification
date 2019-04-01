package com.oca.designPatterns.behavioral.strategy;

public abstract class ValidationStrategy {

    public abstract boolean isValid(CreditCard creditCard);

    /**
     * Check whether the number is a legitimate creditcard number.
     *
     * @param ccNumber credit card number
     * @return true if the number is a credit card number, false otherwise
     */
    protected boolean passesLuhn(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
