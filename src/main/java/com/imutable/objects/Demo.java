package com.imutable.objects;

import java.math.BigDecimal;

public class Demo {

    private boolean isHappyHour;

//    private void reserve(Money cost) {
    private Money reserve(Money cost) { //  a method should not mutate its arguments
        Money finalCost = this.isHappyHour ? cost.scale(.5) : cost;
        System.out.println("Reserving an item costing " + finalCost);
        return finalCost;
    }

    private void buy(Money wallet, Money cost) {
        boolean enoughMoney = wallet.compareTo(cost) >= 0;
//        this.reserve(cost); // do not ignore a return from a method
        Money finalCost = this.reserve(cost);
        boolean finalEnough = wallet.compareTo(finalCost) >= 0;

        if(finalEnough && !enoughMoney) {
            System.out.println("Only this time, you will pay " + finalCost + " with your wallet " + wallet);
        } else if(enoughMoney) {
            System.out.println("You will pay " + finalCost + " with your " + wallet);
        } else {
            System.out.println("You cannot pay " + finalCost + " with your " + wallet);
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Currency usd = new Currency("USD");
        Money usd12 = new Money(new BigDecimal(12), usd);
        Money usd10 = new Money(new BigDecimal(10), usd);
        Money usd7 = new Money(new BigDecimal(7), usd);

        demo.buy(usd12, usd10);
        System.out.println();
        demo.buy(usd7, usd10);

        System.out.println();
        demo.isHappyHour = true;
        demo.buy(usd7, usd10);

        Money usd2 = new Money(new BigDecimal(2), usd);
        Money usd3 = new Money(new BigDecimal(3), usd);
        Money sum2 = usd2.add(usd3);

        Currency eur = new Currency("EUR");
        Money eur2 = new Money(new BigDecimal(2), eur);
        Euro coin = new Euro(new BigDecimal(2), eur, "de");

        System.out.println();
        System.out.println(eur2 + " is " + (eur2.equals(coin) ? "" : "not ") + "equal to " + coin);
        System.out.println(coin + " is " + (coin.equals(eur2) ? "" : "not ") + "equal to " + eur2);
    }
}
