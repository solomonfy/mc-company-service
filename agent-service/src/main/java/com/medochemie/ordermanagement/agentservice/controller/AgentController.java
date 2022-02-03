package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.entity.Country;
import com.medochemie.ordermanagement.agentservice.exception.ForbiddenException;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentRepository repository;


    @GetMapping("/")
    public ResponseEntity<List<Agent>> getAllAgents(){
        try{
            List<Agent> agents = new ArrayList<Agent>();
            if(agents.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                repository.findAll().forEach(agents::add);
                return new ResponseEntity(agents, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable String id){
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add_agent")
    public ResponseEntity<Agent> addNewAgent(@RequestBody Agent agent){
        try{
            if(agent.getAddress() != null && agent.getAgentName() != null){
                agent.setCreatedOn(new Date());
                agent.setAgentCode(generateAgentCode(agent.getAgentName(), agent.getCountryId()));
                agent.setCountryId(agent.getAddress().getCountry());
                Agent _agent = repository.save(agent);
                return new ResponseEntity(_agent, HttpStatus.CREATED);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public String generateAgentCode(String agentName, String countryName) {
//        generate UUID
        UUID temp = UUID.randomUUID();
        String uuidString = Long.toHexString(temp.getMostSignificantBits())
                + Long.toHexString(temp.getLeastSignificantBits());

        String agentPrefix = agentName.length() < 2 ? agentName : agentName.substring(0, 2);
        String countryPrefix = countryName.length() < 2 ? countryName : countryName.substring(0, 2);;
        return agentPrefix + countryPrefix + uuidString;
    }

}
