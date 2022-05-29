package com.imutable.objects;
import java.lang.Override;
import java.math.RoundingMode;

import java.math.BigDecimal;

public class Money implements Comparable<Money>{

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

//    public void scale(double factor) {
    public Money scale(double factor) { // fill state encapsulation - return new instance of money, to avoid sharing the same object reference
//        this.amount = this.amount.multiply((new BigDecimal(factor)).setScale(2, RoundingMode.HALF_UP));
        return new Money(this.amount.multiply(new BigDecimal(factor)), this.currency);
    }

    public Money add(Money other) {
        if(other.currency.compareTo(this.currency) != 0) {
            throw new IllegalArgumentException();
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    private int compareAmountTo(int currencyCompare, Money other) {
        return currencyCompare == 0 ? this.amount.compareTo(other.amount) : currencyCompare;
    }

    @Override
    public int compareTo(Money other) {
        return this.compareAmountTo(this.currency.compareTo(this.currency), other);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    @Override
    public boolean equals(Object other) {
//        return other instanceof Money && this.equals((Money)other); // instance of will not guarantee the same instance of classes, when using inheritance
        return other != null && other.getClass() == this.getClass() && this.equals((Money)other); // other.getClass() == this.getClass() solves the problem above
    }

    private boolean equals (Money other) {
        return this.amount.equals(other.amount) && this.currency.equals(other.currency);
    }
}
