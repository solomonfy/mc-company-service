package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends MongoRepository<Site, String> {
    Optional<Site> findBySiteName(String siteName);
}
