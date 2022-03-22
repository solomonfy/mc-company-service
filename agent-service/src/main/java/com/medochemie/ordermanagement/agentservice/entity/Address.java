package com.medochemie.ordermanagement.agentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Address {

    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String postalCode;
    private String email;
    private String phone;
    private boolean active;
    private boolean primaryAddress;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;
}
