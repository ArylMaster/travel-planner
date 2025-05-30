package com.travel.service;

import com.travel.model.Destination;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.*;

@ApplicationScoped
public class DestinationService {

    private final DynamoDbClient dynamoDb;

    private static final String TABLE_NAME = "Destinations";

    public DestinationService() {
        this.dynamoDb = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
    }

    public void addDestination(Destination destination) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.fromS(destination.id));
        item.put("name", AttributeValue.fromS(destination.name));
        item.put("country", AttributeValue.fromS(destination.country));
        item.put("description", AttributeValue.fromS(destination.description));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDb.putItem(request);
    }

    public List<Destination> getAllDestinations() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse result = dynamoDb.scan(scanRequest);
        List<Destination> destinations = new ArrayList<>();

        for (Map<String, AttributeValue> item : result.items()) {
            Destination dest = new Destination(
                    item.get("id").s(),
                    item.get("name").s(),
                    item.get("country").s(),
                    item.get("description").s()
            );
            destinations.add(dest);
        }

        return destinations;
    }
}
