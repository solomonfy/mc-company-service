package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Territory;
import com.medochemie.ordermanagement.agentservice.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/territories")
public class TerritoryController {
    @Autowired
    private TerritoryRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Territory>> getTerritories(){
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add-territory")
    public ResponseEntity<Territory> addNewTerritory(@RequestBody Territory territory){
        territory.setCreatedOn(new Date());
        return new ResponseEntity(repository.save(territory), HttpStatus.CREATED);
    }
}
