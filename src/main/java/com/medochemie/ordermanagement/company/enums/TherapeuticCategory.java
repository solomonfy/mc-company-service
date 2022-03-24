package com.medochemie.ordermanagement.company.enums;

public enum TherapeuticCategory {
    ANTI_PAIN("ANTI_PAIN"),
    CARDIO_VASCULAR("CARDIO_VASCULAR"),
    CNS_DRUGS("CNS_DRUGS"),
    ANTI_DIABETIC("ANTI_DIABETIC");

    private final String category;

    TherapeuticCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

}
