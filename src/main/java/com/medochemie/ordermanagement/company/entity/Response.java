package com.medochemie.ordermanagement.company.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

//this class will be used as a response body for all REST requests
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
//the two annotations makes sure that only non-null fields of the class will be sent in response body
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}
