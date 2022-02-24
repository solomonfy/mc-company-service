package com.medochemie.ordermanagement.OrderService.controller.utils;

import com.medochemie.ordermanagement.OrderService.entity.Agent;
import java.util.Calendar;

public class GenerateOrderNumber {

    public static String generateOrderNumber(String countryCode,Agent agent){

        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String agentName;
        agentName = firstTwoChars(agent.getAgentName());
        return countryCode + "/" + agentName +"/" + currentYear;
    }

    public static String firstTwoChars(String str) {
        return str.length() < 2 ? str : str.substring(0, 2);
    }

}
