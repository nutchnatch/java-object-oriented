package com.optionalobjects;

import com.nullchecks.Warranty;

import java.time.LocalDate;

public class Part {

    private LocalDate installmentDate; // date of part installation
    private LocalDate defectDetectedOn; // date when a defect was diagnosed

    public Part(LocalDate installmentDate) {
        this(installmentDate, null);
    }

    public Part(LocalDate installmentDate, LocalDate defectDetectedOn) {
        this.installmentDate = installmentDate;
        this.defectDetectedOn = defectDetectedOn;
    }

    public Part defective(LocalDate detectedOn) {
        return new Part(this.installmentDate, detectedOn);
    }

    public LocalDate getDefectDetectedOn() {
        return defectDetectedOn;
    }


    public Warranty apply(Warranty extendedWarranty) {
        return this.defectDetectedOn == null? Warranty.VOID : Warranty.lifetime(this.defectDetectedOn);
    }
}
