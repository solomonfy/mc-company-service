package com.medochemie.ordermanagement.company.controller;

import com.medochemie.ordermanagement.company.entity.Site;
import com.medochemie.ordermanagement.company.repository.SiteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/sites")
public class SiteController {

    private static final Logger logger = (Logger) LogManager.getLogger(SiteController.class);

    @Autowired
    private SiteRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<Site>> getAllSites(){
        logger.info("Inside SiteController getAllSites method!");
        logger.debug(Object::new);
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{siteName}")
    public ResponseEntity<Site> getSiteByName(@PathVariable String siteName){
        return new ResponseEntity(repository.findBySiteName(siteName), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable String id){
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public void editSite(@PathVariable String id, @RequestBody Site editedSite){
        Optional<Site> foundSite = repository.findById(id);

    }

    @PostMapping("/add-site")
    public ResponseEntity<Site> addNewSite(@RequestBody Site site){
        return new ResponseEntity<>(repository.save(site), HttpStatus.CREATED);
    }

    @PostMapping("/add-sites")
    public ResponseEntity<List<Site>> addSites(@RequestBody List<Site> sites){
        return new ResponseEntity<>(repository.saveAll(sites), HttpStatus.CREATED);
    }
}
