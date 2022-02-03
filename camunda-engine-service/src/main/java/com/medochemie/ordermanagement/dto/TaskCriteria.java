package com.medochemie.ordermanagement.dto;

import lombok.Data;

@Data
public class TaskCriteria {
    private String searchValue;
    private int start;
    private int pageSize;
    private String sort;
    private String sortOrder;
    private String assignee;
}
