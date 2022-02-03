package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Country;
import com.medochemie.ordermanagement.agentservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Country>> getCountries(){
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add-country")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        country.setCreatedOn(new Date());
        return new ResponseEntity(repository.save(country), HttpStatus.CREATED);
    }

    @PostMapping("/add-countries")
    public ResponseEntity<List<Country>> addCountries(@RequestBody Iterable<Country> countries ){
        return new ResponseEntity(repository.saveAll(countries), HttpStatus.CREATED);
    }

}
