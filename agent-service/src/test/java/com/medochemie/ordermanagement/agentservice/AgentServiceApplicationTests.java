package com.medochemie.ordermanagement.agentservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AgentServiceApplicationTests {

    @Autowired
    AgentRepository repository;

    @Test
    void contextLoads() {
        assertThat(5 > 1).isTrue();
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        addNewAgent();
    }

    @Test
    public void shouldReturnAllAgents() {
        List<Agent> list = repository.findAll();
        assertThat(list).isNotNull();
    }

    public void addNewAgent() {
        String agentsList = "[{\n" +
                "    \"agentName\": \"Test\",\n" +
                "    \"agentCode\": \"TE/ET/0002\",\n" +
                "    \"countryId\": null,\n" +
                "    \"active\": true,\n" +
                "    \"createdBy\": null,\n" +
                "    \"createdOn\": \"2022-02-02T17:43:29.296+00:00\",\n" +
                "    \"updatedOn\": null,\n" +
                "    \"updatedBy\": null\n" +
                "}, {\n" +
                "    \"agentName\": \"Test2\",\n" +
                "    \"agentCode\": \"TE2/ET/0003\",\n" +
                "    \"countryId\": null,\n" +
                "    \"active\": true,\n" +
                "    \"createdBy\": null,\n" +
                "    \"createdOn\": \"2022-02-02T17:43:29.296+00:00\",\n" +
                "    \"updatedOn\": null,\n" +
                "    \"updatedBy\": null\n" +
                "}]";
        try {
            Agent agents[] = new ObjectMapper().readValue(agentsList, Agent[].class);
            for (Agent agent : agents) {
                repository.save(agent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnAgentByName() {
        List<Agent> list = repository.findAll();
        assertThat("Test").isEqualTo(list.get(0).getAgentName());
    }

}
