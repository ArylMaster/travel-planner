package com.travel.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;

@ApplicationScoped
public class DynamoDbService {

    private DynamoDbClient client;

    @PostConstruct
    void init() {
        this.client = DynamoDbClient.builder()
            .region(Region.US_EAST_1) // change region as needed
            .build();
        System.out.println(">>> DynamoDB Client initialized");
    }

    public DynamoDbClient getClient() {
        return client;
    }
}
