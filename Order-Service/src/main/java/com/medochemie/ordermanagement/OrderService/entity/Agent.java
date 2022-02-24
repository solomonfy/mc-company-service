package com.medochemie.ordermanagement.OrderService.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

    private String agentId;
    private String agentName;
    private String agentCode;
//    private List<String> orders;
}
