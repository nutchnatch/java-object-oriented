package com.optionalobjects;

import com.nullchecks.TimeLimitWarranty;
import com.nullchecks.Warranty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.run();
    }

    public void claimWarranty(Article article) {
        LocalDate today = LocalDate.now();
        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
        article.getExpressWarranty().on(today).claim(this::offerRepair);
        article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);

        System.out.println("-----------------");
    }

    private void offerMoneyBack() {
        System.out.println("Offer money back");
    }

    private void offerRepair() {
        System.out.println("Offer repair");
    }

    private void offerSensorRepair() {
        System.out.println("Offer sensor replacement");
    }


    public void run() {
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyBack1 = new TimeLimitWarranty(sellingDate, Duration.ofDays(60));
        Warranty warranty1 = new TimeLimitWarranty(sellingDate, Duration.ofDays(365));

        Part sensor = new Part(sellingDate);
        Warranty sensorWarranty = new TimeLimitWarranty(sellingDate, Duration.ofDays(90));

        Article item = new Article(moneyBack1, warranty1).install(sensor, sensorWarranty);

        this.claimWarranty(item);
        this.claimWarranty(item.withVisibilityDamage());
        this.claimWarranty(item.notOperational().withVisibilityDamage());
        this.claimWarranty(item.notOperational());

        LocalDate sensorExamined = LocalDate.now().minus(2, ChronoUnit.DAYS);
        this.claimWarranty(item.sensorNotOperational(sensorExamined));
        this.claimWarranty(item.notOperational().sensorNotOperational(sensorExamined));
    }
}
