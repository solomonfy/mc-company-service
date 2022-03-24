package com.medochemie.ordermanagement.company.enums;

public enum Formulation {
    TABLET("TABLET"),
    CAPSULE("CAPSULE"),
    SUSPENSION("SUSPENSION"),
    SUPPOSITORY("SUPPOSITORY"),
    SYRUP("SYRUP"),
    GEL("GEL"),
    VAGINAL_SUPPOSITORY("VAGINAL_SUPPOSITORY");

    private final String formulation;

    Formulation(String formulation) {
        this.formulation = formulation;
    }
    public String getFormulation(){
        return this.formulation;
    }
}
