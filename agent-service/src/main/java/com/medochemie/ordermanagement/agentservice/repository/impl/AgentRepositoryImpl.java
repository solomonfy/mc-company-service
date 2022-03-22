package com.medochemie.ordermanagement.agentservice.repository.impl;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepositoryCustom;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class AgentRepositoryImpl implements AgentRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long updateAgent(Agent agent) {
        Query query = new Query(Criteria.where("agent").is(agent));
        Update update = new Update();
        update.set("agent", agent);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Agent.class);

        if(result !=null) {
            return result.getModifiedCount();
        }
        else return 0;
    }
}
