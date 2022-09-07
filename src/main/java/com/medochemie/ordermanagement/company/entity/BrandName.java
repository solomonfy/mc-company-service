package com.medochemie.ordermanagement.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BrandName {

    @Id
    private String id;
    private String brandName;
    private String countryId;
}
