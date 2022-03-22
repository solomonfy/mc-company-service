package com.medochemie.ordermanagement.agentservice.service___;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AgentService {
    Agent addNewAgent(Agent agent);

    List<Agent> getAllAgents();

//    List<Agent> getAllAgents(String id, String countryId, String status);

    Agent updateAgent(Agent agent);

    Agent deleteAgent(String id);
}
