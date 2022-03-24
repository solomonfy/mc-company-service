package com.medochemie.ordermanagement.company.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Department")
public class Department {
    @Id
    private String id;
    private String departmentName;
    private String siteId;
    private String departmentCode;
    private String email;
    private String phone;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

}
