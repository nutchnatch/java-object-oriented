package com.nullchecks;

import java.time.LocalDate;

public interface Warranty {

    Warranty on(LocalDate date);

    default void claim(Runnable action) {
        action.run();
    }

    public boolean isValidOn(LocalDate date);

    Warranty VOID = new VoidWarranty();
}
