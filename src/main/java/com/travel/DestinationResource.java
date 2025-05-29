package com.travel;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Path("/destinations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinationResource {

    private List<String> destinations = new ArrayList<>();

    public DestinationResource() {
        System.out.println(">>> DestinationResource initialized");
    }

    @GET
    public List<String> getAll() {
        System.out.println(">>> getAll called, size: " + destinations.size());
        return destinations;
    }

    @POST
    public void add(String destination) {
        destinations.add(destination);
        System.out.println(">>> add called, new size: " + destinations.size());
    }
}
