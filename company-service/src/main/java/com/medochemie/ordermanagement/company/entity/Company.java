package com.medochemie.ordermanagement.company.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Company")
public class Company {
    @Id
    private String id;
    private String fullName;
    private String website;
    private Address address;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

}
