package com.medochemie.ordermanagement.rest;

import com.medochemie.ordermanagement.dto.CustomTaskObject;
import com.medochemie.ordermanagement.dto.TaskCriteria;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.engine.variable.type.ValueType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Slf4j
public class RequestController {
    private static String taskResponse = "taskResponse";
    private static String actionFailed = "actionFailed";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(RequestController.class);

    @RequestMapping(value = "/taskWithDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomTaskObject>> updateTask(@RequestBody TaskCriteria taskCriteria) {
//        logger.info("Message to be printed");
        log.info("Message");
        List<CustomTaskObject> taskDtos = new ArrayList<>();
        try {
            TaskQuery query = taskService.createTaskQuery();
            if (null != taskCriteria.getSearchValue()) {
                query.or()
                        .processVariableValueLike(Constants.AGENT_NAME,
                                convertToNameLike(taskCriteria.getSearchValue()))
                        .processVariableValueLike(Constants.REQUEST_ID,
                                convertToNameLike(taskCriteria.getSearchValue()))
                        .endOr();

            }
            return new ResponseEntity<>(taskDtos, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

    }

    private String convertToNameLike(String match) {
        match = "%" + match + "%";
        return match;
    }
}
