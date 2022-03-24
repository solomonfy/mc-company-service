package com.medochemie.ordermanagement.company.entity;

import com.medochemie.ordermanagement.company.constant.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Employee")
public class Employee {

    @Id
    private String id;
    private String departmentId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String countryCode;
    private List<Role> roles;
    private String phone;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

    public void setEmailId() {
        this.emailId = this.firstName + "." + this.lastName + "@" + this.countryCode+ ".@medochemie.com";
    }
}
