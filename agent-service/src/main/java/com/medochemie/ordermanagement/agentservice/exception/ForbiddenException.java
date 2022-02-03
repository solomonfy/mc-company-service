package com.medochemie.ordermanagement.agentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Sorry, your request can't be processed. Missing address")
public class ForbiddenException extends RuntimeException{
}
