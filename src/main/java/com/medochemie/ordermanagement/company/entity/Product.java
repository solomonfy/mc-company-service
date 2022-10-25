package com.medochemie.ordermanagement.company.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medochemie.ordermanagement.company.enums.Formulation;
import com.medochemie.ordermanagement.company.enums.TherapeuticCategory;

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
@Document(collection = Constants.PRODUCT_ENTITY)
public class Product {
    @Id
    private String id;
    private String chemicalName;
    private String genericName;
    private Formulation formulation;
    private String brandName;
    private String strength;
    private String packSize;
    private Float unitPrice;
    private List<Site> productionSites;
    private boolean active;
    private TherapeuticCategory category;
    private List<String> imageUrls;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
}
