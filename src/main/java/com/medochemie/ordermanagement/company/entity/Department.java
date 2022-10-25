package com.medochemie.ordermanagement.company.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medochemie.ordermanagement.company.utils.Constants;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
@Document(collection = Constants.DEPARTMENT_ENTITY)
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
