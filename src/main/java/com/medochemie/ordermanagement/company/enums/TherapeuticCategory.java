package com.medochemie.ordermanagement.company.enums;

public enum TherapeuticCategory {
    ANTI_PAIN("ANTI PAIN"),
    CARDIO_VASCULAR("CARDIO VASCULAR"),
    CNS_DRUGS("CNS DRUGS"),
    ANTI_DIABETIC("ANTI DIABETIC");

    private final String category;

    TherapeuticCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

}
