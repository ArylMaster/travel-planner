package com.travel.service;

import com.travel.model.Destination;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DestinationService {

    private DynamoDbEnhancedClient enhancedClient;
    private DynamoDbTable<Destination> destinationTable;

    @PostConstruct
    public void init() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(Region.US_WEST_2)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create("dummy-key", "dummy-secret")))
                .build();

        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.destinationTable = enhancedClient.table("Destinations", TableSchema.fromBean(Destination.class));

        // Create table if not exists
        try {
            dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName("Destinations").build());
        } catch (ResourceNotFoundException e) {
            dynamoDbClient.createTable(CreateTableRequest.builder()
                    .tableName("Destinations")
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("id")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("id")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(5L)
                            .writeCapacityUnits(5L)
                            .build())
                    .build());
        }
    }

    public void addDestination(Destination destination) {
        destinationTable.putItem(destination);
    }

    public List<Destination> getAllDestinations() {
        return destinationTable.scan()
                .items()
                .stream()
                .collect(Collectors.toList());
    }

    public void updateDestination(Destination destination) {
        destinationTable.updateItem(destination);
    }

    public void deleteDestination(String id) {
        destinationTable.deleteItem(Key.builder().partitionValue(id).build());
    }

    public Destination getDestination(String id) {
        return destinationTable.getItem(Key.builder().partitionValue(id).build());
    }
}
