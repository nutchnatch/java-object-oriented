package com.nullchecks;

import java.time.LocalDate;
import java.time.Duration;

public class TimeLimitWarranty implements Warranty{
    private LocalDate dateIssued;
    private Duration validFor;

    public TimeLimitWarranty(LocalDate dateIssued, Duration validFor) {
        this.dateIssued = dateIssued;
        this.validFor = validFor;
    }

    @Override
    public Warranty on(LocalDate date) {
        return null;
    }

    @Override
    public boolean isValidOn(LocalDate date) {
        return this.dateIssued.compareTo(date) <= 0 &&
                this.getExpiredDate().compareTo(date) > 0;
    }

    public LocalDate getExpiredDate() {
        return dateIssued.plusDays(this.getValidForDays());
    }

    public long getValidForDays() {
        return validFor.toDays();
    }
}
