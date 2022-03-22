package com.medochemie.ordermanagement.agentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Agent")
public class Agent {
    @Id
    private String id;
    private String agentName;
    private String agentCode;
    private List<Address> address;
    private String countryId;
    private boolean active;
    private String createdBy;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

}
