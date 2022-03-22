package com.medochemie.ordermanagement.agentservice.repository;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends MongoRepository<Agent, String> {

    @Query(value = "{ 'agentName' : ?0 }")
    Agent findByAgentName(String agentName);
}
