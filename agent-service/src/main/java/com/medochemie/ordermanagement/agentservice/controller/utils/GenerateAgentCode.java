package com.medochemie.ordermanagement.agentservice.controller.utils;

import java.util.UUID;

public class GenerateAgentCode {

    public static String generateAgentCode(String agentName, String countryName) {
        UUID temp = UUID.randomUUID();
        String uuidString = Long.toHexString(temp.getMostSignificantBits())
                + Long.toHexString(temp.getLeastSignificantBits());

        String agentPrefix = agentName.length() < 2 ? agentName : agentName.substring(0, 2);
        String countryPrefix = countryName.length() < 2 ? countryName : countryName.substring(0, 2);;
        return agentPrefix + countryPrefix + uuidString;
    }
}
