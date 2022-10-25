package com.medochemie.ordermanagement.company.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medochemie.ordermanagement.company.constant.Role;
import com.medochemie.ordermanagement.company.utils.Constants;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
@Document(collection = Constants.EMPLOYEE_ENTITY)
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
