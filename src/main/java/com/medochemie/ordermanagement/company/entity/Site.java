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
@Document(collection = Constants.SITE_ENTITY)
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
