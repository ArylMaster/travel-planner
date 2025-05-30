package com.travel;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.travel.model.Destination;
import com.travel.service.DestinationService;

import java.util.List;

@ApplicationScoped
@Path("/destinations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinationResource {

    @Inject
    DestinationService destinationService;

    public DestinationResource() {
        System.out.println(">>> DestinationResource initialized");
    }

    @GET
    public List<Destination> getAll() {
        System.out.println(">>> getAll called");
        return destinationService.getAllDestinations();
    }

    @POST
    public Response addDestination(@Valid Destination destination) {
        destinationService.addDestination(destination);
        System.out.println(">>> addDestination called");
        return Response.status(Status.CREATED).build();
    }
}
