package com.medochemie.ordermanagement.company.controller;

import com.medochemie.ordermanagement.company.entity.Company;
import com.medochemie.ordermanagement.company.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@Slf4j
public class CompanyController {
    @Autowired
    public CompanyRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<Company>> getAllCompanies(){
        log.info("Getting all companies");
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id){
        log.info("Getting one company by ID");
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add-company")
    public ResponseEntity<Company> addNew(@RequestBody Company company){
        return new ResponseEntity(repository.save(company), HttpStatus.CREATED);
    }

    @GetMapping("/get-by-address/{city}")
    public  ResponseEntity<List<Company>> getCompanyByAddress(@PathVariable String city){
        return new ResponseEntity(repository.findByCity(city), HttpStatus.OK);
    }
}
