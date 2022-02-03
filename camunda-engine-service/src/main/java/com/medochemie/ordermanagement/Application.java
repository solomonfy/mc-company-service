package com.medochemie.ordermanagement;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableEurekaClient
@EnableProcessApplication
@EnableConfigurationProperties
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}