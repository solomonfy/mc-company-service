package com.medochemie.ordermanagement.company.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Site")
public class Site {
    @Id
    private String id;
    private String companyId;
    private String siteName;
    private String siteCode;
    private Address address;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;
}
