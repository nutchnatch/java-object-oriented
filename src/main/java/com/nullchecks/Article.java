package com.nullchecks;

public class Article {

    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        // There will never be an object a null warranty
        if(moneyBackGuarantee == null) throw new IllegalArgumentException();
        if(expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
    }

    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }

    public Warranty getExpressWarranty() {
        return expressWarranty;
    }
}
