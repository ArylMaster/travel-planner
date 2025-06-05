package com.travel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Destination {

    private String id;
    private String name;
    private String country;
    private String description;

    @NotBlank(message = "ID must not be blank")
    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Country must not be blank")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @NotBlank(message = "Description must not be blank")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Required by DynamoDbBean (no-arg constructor)
    public Destination() {}

    public Destination(String id, String name, String country, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
