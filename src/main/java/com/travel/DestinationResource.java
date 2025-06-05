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

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        for (Destination destination : destinationService.getAllDestinations()) {
            if (destination.getId().equals(id)) {
                return Response.ok(destination).build();
            }
        }
        return Response.status(Status.NOT_FOUND).entity("Destination not found").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDestination(@PathParam("id") String id, @Valid Destination updated) {
        for (Destination destination : destinationService.getAllDestinations()) {
            if (destination.getId().equals(id)) {
                destination.setName(updated.getName());
                destination.setCountry(updated.getCountry());
                destination.setDescription(updated.getDescription());
                return Response.ok(destination).build();
            }
        }
        return Response.status(Status.NOT_FOUND).entity("Destination not found").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDestination(@PathParam("id") String id) {
        List<Destination> destinations = destinationService.getAllDestinations();
        for (int i = 0; i < destinations.size(); i++) {
            if (destinations.get(i).getId().equals(id)) {
                destinations.remove(i);
                return Response.noContent().build();
            }
        }
        return Response.status(Status.NOT_FOUND).entity("Destination not found").build();
    }


}
