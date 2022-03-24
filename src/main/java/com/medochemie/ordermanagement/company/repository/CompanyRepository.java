package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository <Company, String> {

    @Query("{'Address.city': ?0}")
    List<Company> findByCity(String city);
}