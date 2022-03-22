package com.medochemie.ordermanagement.agentservice.service___.impl;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import com.medochemie.ordermanagement.agentservice.service___.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AgentServiceImpl implements AgentService {

    private final java.util.logging.Logger LOGGER = Logger.getLogger(String.valueOf(AgentServiceImpl.class));

    @Autowired
    private AgentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Agent addNewAgent(Agent agent) {
        agent.setId(null);
        agent.setAgentCode(null);
        agent.setAgentCode(null);
        agent.setCountryId(null);
        agent.setAgentName(null);
        agent.setActive(false);
        return repository.save(agent);
    }

    @Override
    public List<Agent> getAllAgents() {
        List<Agent> agents = repository.findAll();
        return agents;
    }

//    @Override
//    public List<Agent> getAllAgents(String _id, String countryId, String status) {
//        Query query = new Query().with(Sort.by("_id", "1"));
//        query = (countryId == null) ? query : query.addCriteria(Criteria.where("countryId").is(countryId));
//        LOGGER.info("query : {}" + query);
//        List<Agent> agents = mongoTemplate.find(query, Agent.class);
//        return agents;
//    }

    @Override
    public Agent updateAgent(Agent agent) {
        Agent foundAgent = repository.findById(agent.getId()).orElse(null);
        if (foundAgent != null) {
            agent.setAgentName(foundAgent.getAgentName());
            agent.setUpdatedOn(new Date());
            //add other attributes

            repository.save(agent);
        }
        return foundAgent;
    }

    @Override
    public Agent deleteAgent(String id) {
        Agent foundAgent = repository.findById(id).orElse(null);
        if (foundAgent != null) repository.deleteById(id);
        return foundAgent;
    }
}
