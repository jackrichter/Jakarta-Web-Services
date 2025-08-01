/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travel.rest;

import com.travel.entity.Flight;
import com.travel.entity.Hotel;
import com.travel.service.TravelService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 *
 * @author LI Learning
 */
@Path("travel")
public class TravelResource {

    @Inject
    private TravelService travelService;
    
    @GET
    public String test() {
       return "This is a test!";
    }

    @GET
    @Path("flights")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> findAllFlights() {
        return travelService.findAll();
    }

    @GET
    @Path("flights/{to}")
    public Response getFlightsByDestination(@PathParam("to") String destination) {

        if (destination.length() > 3) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid destination. Please supply a valid IATA code.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        } else {
            NewCookie cookie = new NewCookie("username", "Bob");
            List<Flight> flights = travelService.findFlightsByDestination(destination);
            return Response.status(Response.Status.OK)
                    .entity(flights)
                    .type(MediaType.APPLICATION_JSON)
                    .cookie(cookie)
                    .build();
        }
    }

    @GET
    @Path("airline")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlightsByAirline(@QueryParam("al") String airline) {
        return travelService.findFlightsByAirline(airline);
    }

    @GET
    @Path("airline-destination")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlightsByAirlineAndDestination(@QueryParam("al") String airline, @QueryParam("dest") String destination) {
        return travelService.findFlightsByAirlineAndDestination(airline, destination);
    }

    @GET
    @Path("json/bugetflight/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getCheapestFlightForDestinationJSON(@PathParam("to") String destination) {
        return travelService.findCheapestFlightForDestination(destination);
    }

    @GET
    @Path("xml/bugetflight/{to}")
    @Produces(MediaType.APPLICATION_XML)
    public Flight getCheapestFlightForDestinationXML(@PathParam("to") String destination) {
        return travelService.findCheapestFlightForDestination(destination);
    }

    @GET
    @Path("flights/between/{min}/{max}")
    public Response getFlightsInPriceRangeForDestination(@QueryParam("dest") String destination,
                                                         @PathParam("min") Double minVal, @PathParam("max") Double maxVal) {

        if (destination.length() > 3) {
            return Response.serverError()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid destination. Please supply a valid IATA code")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        } else {
            List<Flight> flights = travelService.findFlightsForPriceRange(destination, minVal, maxVal);
            return Response.status(Response.Status.OK)
                    .entity(flights)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Path("flight/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFlight(Flight flight) {
        travelService.createFlight(flight);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("flight/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFlight(Flight flight) {
        travelService.updateFlight(flight);
        return Response.ok().build();
    }

    @DELETE
    @Path("flight/delete/{id}")
    public Response deleteFlight(@PathParam("id") long id) {
        travelService.deleteFlight(id);
        return Response.ok().build();
    }

    @POST
    @Path("hotel/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHotel(Hotel hotel) {
        travelService.createHotel(hotel);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("hotel/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateHotel(Hotel hotel) {
        travelService.updateHotel(hotel);
        return Response.ok().build();
    }

    @DELETE
    @Path("hotel/delete/{id}")
    public Response deleteHotel(@PathParam("id") long id) {
        travelService.deleteHotel(id);
        return Response.ok().build();
    }
}
