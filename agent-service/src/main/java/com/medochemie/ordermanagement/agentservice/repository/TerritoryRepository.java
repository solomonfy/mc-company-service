package com.medochemie.ordermanagement.agentservice.repository;

import com.medochemie.ordermanagement.agentservice.entity.Territory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritoryRepository extends MongoRepository<Territory, String> {
}
