package com.travel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Destination {
    @NotBlank(message = "ID must not be blank")
    public String id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    public String name;

    @NotBlank(message = "Country must not be blank")
    public String country;

    @NotBlank(message = "Description must not be blank")
    public String description;

    public Destination() {}

    public Destination(String id, String name, String country, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
