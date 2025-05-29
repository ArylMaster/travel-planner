package com.travel;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.travel.model.Destination;

@ApplicationScoped
@Path("/destinations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinationResource {

    private List<Destination> destinations = new ArrayList<>();

    public DestinationResource() {
        System.out.println(">>> DestinationResource initialized");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destination> getAll() {
        System.out.println(">>> getAll called, size: " + destinations.size());
        return destinations;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDestination(@Valid Destination destination) {
        destinations.add(destination);
        System.out.println(">>> addDestination called, new size: " + destinations.size());
        return Response.status(Status.CREATED).build();
    }
}
