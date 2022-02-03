package com.medochemie.ordermanagement.rest;

import lombok.Data;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.client.CloseableHttpClient;

@Configuration
@Data
public class CustomRestTemplate {

//    @Value("${resttemplate.timeOut}")
    private int timeOut;

    @Bean
    public RestTemplate getRestTemplate(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setHttpClient(httpClient());
        return new RestTemplate(httpRequestFactory);
    }

    @Bean
    public CloseableHttpClient httpClient() {
        CloseableHttpClient client = HttpClientBuilder.create()
                        .setDefaultRequestConfig(requestConfig()).build();
        return client;
    }

    @Bean
    public RequestConfig requestConfig() {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(getTimeOut())
                .setSocketTimeout(getTimeOut()).build();
        return config;
    }

}
