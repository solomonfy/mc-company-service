package com.medochemie.ordermanagement.company.entity;

import com.medochemie.ordermanagement.company.enums.Formulation;
import com.medochemie.ordermanagement.company.enums.TherapeuticCategory;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Product")
public class Product {
    @Id
    private String id;
    private String chemicalName;
    private String genericName;
    private String productCode;
    private String brandName;
    private String strength;
    private String packSize;
    private Float unitPrice;
    private Formulation formulation;
    private TherapeuticCategory therapeuticCategory;
    private List<Site> productionSites;
    private List<String> imageUrls;
    private boolean active;
    private String HSCode;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
}
