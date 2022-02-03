package com.medochemie.ordermanagement.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

@Named("GenerateProforma")
public class GenerateProforma implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Generate Proforma");


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String country = (String) execution.getVariable("country");
        String agent = (String) execution.getVariable("agent");
        String item = (String) execution.getVariable("item");
        String action = (String) execution.getVariable("action");
        String approver = (String) execution.getVariable("approver");
        Long qty = (Long) execution.getVariable("qty");
        String proformaDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(LocalDateTime.now());


        LOGGER.info("Inside GenerateProforma class!" );
        System.out.println("Country: " + country);
        System.out.println("Agent: " + agent);
        System.out.println("Item: " + item);
        System.out.println("Quantity: " + qty);
        System.out.println("Date: " + proformaDate);
    }
}
