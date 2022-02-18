package com.medochemie.ordermanagement.OrderService.enums;

public enum Status {
    Draft("Draft"),
    Under_Review("Under Review"),
    Active("Active"),
    Completed("Completed"),
    Rejected("Rejected");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
