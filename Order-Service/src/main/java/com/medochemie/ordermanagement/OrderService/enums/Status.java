package com.medochemie.ordermanagement.OrderService.enums;

public enum Status {

    DRAFT(1, "Draft"),
    UNDER_REVIEW(2, "Under Review"),
    ACTIVE(3, "Active"),
    COMPLETED(4, "Completed"),
    REJECTED(5, "Rejected");

    private final int id;
    private final String display;

    Status(int id, String display) {
        this.id = id;
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public String getDisplay() {
        return display;
    }

    //    Draft("Draft"),
    //    Under_Review("Under Review"),
    //    Active("Active"),
    //    Completed("Completed"),
    //    Rejected("Rejected");

    //    private final String status;
    //
    //    Status(String status){
    //        this.status = status;
    //    }

    //    public String getStatus(){
    //        return this.status;
    //    }

}
