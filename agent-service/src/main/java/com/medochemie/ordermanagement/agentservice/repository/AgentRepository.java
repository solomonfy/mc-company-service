package com.medochemie.ordermanagement.agentservice.repository;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends MongoRepository<Agent, String> {
//    List<Agent> findAgentByName(String fullName);
}
