package com.imutable.objects;

import java.math.BigDecimal;

public class Euro extends Money {

    private String iso2Country;

    public Euro(BigDecimal amount, Currency currency, String iso2Country) {
        super(amount, currency);
        this.iso2Country = iso2Country;
    }

    @Override
    public boolean equals(Object other) {
//        return other instanceof Euro && this.equals((Euro) other);
        return other != null && other.getClass() == this.getClass() && this.equals((Euro) other);
    }

    private boolean equals (Euro other) {
        return super.equals(other) && this.iso2Country.equals(other.iso2Country);
    }
}
