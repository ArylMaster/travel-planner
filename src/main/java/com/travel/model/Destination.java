package com.travel.model;

public class Destination {
    public String id;
    public String name;
    public String country;
    public String description;

    public Destination() {}

    public Destination(String id, String name, String country, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
