package com.medochemie.ordermanagement.company.entity;

import com.medochemie.ordermanagement.company.utils.Constants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = Constants.PRODUCT_REGISTRATION)
public class ProductRegistration {

    @Id
    private String id;
    private String productId;
    private String countryId;
    private String brandName;
    private String registrationNumber;
    private Date registeredOn;
    private Date expiresOn;
    private Date createdOn;
}
