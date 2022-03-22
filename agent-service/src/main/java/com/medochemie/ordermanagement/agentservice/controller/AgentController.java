package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Address;
import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import com.medochemie.ordermanagement.agentservice.service___.impl.AgentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.medochemie.ordermanagement.agentservice.controller.utils.GenerateAgentCode.generateAgentCode;


@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    @Autowired
    private AgentRepository repository;


    @GetMapping("/")
    public ResponseEntity<List<Agent>> findAllAgents() {
//        List<Agent> agents = repository.findAll();
//        System.out.println(agents);
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> findAgentById(@PathVariable String id) {
        System.out.println(id);
        Optional<Agent> optionalAgent = repository.findById(id);
        System.out.println(optionalAgent);
        Agent agent = optionalAgent.get();
        System.out.println(agent);

        return new ResponseEntity(agent, HttpStatus.OK);
    }

    @GetMapping("/agent-name/{agentName}")
    public ResponseEntity<Agent> findAgentByName(@PathVariable String agentName) {
        return new ResponseEntity(repository.findByAgentName(agentName), HttpStatus.OK);
    }

    @PostMapping("/new-agent")
    public ResponseEntity<Agent> addNewAgent(@RequestBody Agent agent) {
        List<Address> addressList = agent.getAddress();
        try {
            if (agent.getAddress() != null && agent.getAgentName() != null) {
                agent.setCreatedOn(new Date());
                agent.setAgentCode(generateAgentCode(agent.getAgentName(), agent.getCountryId()));
                for (Address address : addressList) {
                    if (address.isPrimaryAddress()) {
                        agent.setCountryId(address.getCountry());
                    }
                }
                Agent newAgent = repository.save(agent);

                return new ResponseEntity(newAgent, HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
