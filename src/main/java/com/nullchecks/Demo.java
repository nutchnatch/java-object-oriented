package com.nullchecks;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.run();
    }

    /**
     * Accumulation of methods arguments is sure a sign of design flow
     * Boolean methods arguments are a sure sign of missing objects (don't pass them as methods arguments)
     * We shall tell object what to do and they choose know how to do it
     * Who should know the state of the Article? The Article itself
     * Article class know its condition and operability and never returns null from methods returnind Warranties
     * @param article
//     * @param isGooDCondition
//     * @param isNonOperational
     */
//    public void claimWarranty(Article article, boolean isGooDCondition, boolean isNonOperational) {
    public void claimWarranty(Article article) {
        LocalDate today = LocalDate.now();

        // branching over boolean flags and null checks are poor programming technics and it is not object oriented programming
        if(
//                isGooDCondition && isNonOperational &&
//                article.getMoneyBackGuarantee() != null &&
                // isValidOn should return an object, a non-null result
                article.getMoneyBackGuarantee().isValidOn(today)) {
            System.out.println("Offer money back");
        }

        if(
//                isNonOperational &&
//                article.getMoneyBackGuarantee() != null &&
                article.getExpressWarranty().isValidOn(today)) {
            System.out.println("Offer repair");
        }

        System.out.println("-----------------");
    }

    public void run() {
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyBack1 = new TimeLimitWarranty(sellingDate, Duration.ofDays(30));
        Warranty warranty1 = new TimeLimitWarranty(sellingDate, Duration.ofDays(365));
        Article item1 = new Article(moneyBack1, warranty1);

        this.claimWarranty(item1);

        // Replace nulls with proper objects
        // Implements the expected interface
        // Provide replacement behavior in methods
        Article item2 = new Article(VoidWarranty.VOID, VoidWarranty.VOID);
        this.claimWarranty(item2);
    }
}
