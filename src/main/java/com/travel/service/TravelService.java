package com.travel.service;

import com.travel.entity.Flight;
import com.travel.entity.Hotel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TravelService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Flight> findAll() {
        return entityManager.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }

    public List<Flight> findFlightsByDestination(String destination) {
        String strQuery = "SELECT * FROM Flight f WHERE f.destination = ?";
        Query query = entityManager.createNativeQuery(strQuery);
        query.setParameter(1, destination);

        return query.getResultList();
    }

    public List<Flight> findFlightsByAirline(String airline) {
        String strQuery = "SELECT * FROM Flight f WHERE f.airline = ?";
        Query query = entityManager.createNativeQuery(strQuery);
        query.setParameter(1, airline);

        return query.getResultList();
    }

    public List<Flight> findFlightsByAirlineAndDestination(String airline, String destination) {
        String strQuery = "SELECT * FROM Flight f WHERE f.airline = ? AND f.destination = ?";
        Query query = entityManager.createNativeQuery(strQuery);
        query.setParameter(1, airline);
        query.setParameter(2, destination);

        return query.getResultList();
    }

    public Flight findCheapestFlightForDestination(String destination) {
        String strQuery = "SELECT * FROM Flight f1 WHERE f1.destination = ? " +
                "AND f1.price = (SELECT MIN(f2.price) FROM Flight f2 WHERE f2.destination = ?)";

        Query query = entityManager.createNativeQuery(strQuery, Flight.class);
        query.setParameter(1, destination);
        query.setParameter(2, destination);

        return (Flight) query.getSingleResult();
    }

    public List<Flight> findFlightsForPriceRange(String destination, Double minPrice, Double maxPrice) {
        String strQuery = "SELECT * FROM Flight f WHERE f.destination = ? AND f.price BETWEEN ? AND ?";

        Query query = entityManager.createNativeQuery(strQuery, Flight.class);
        query.setParameter(1, destination);
        query.setParameter(2, minPrice);
        query.setParameter(3, maxPrice);

        return (List<Flight>) query.getResultList();
    }

    @Transactional
    public void createFlight(Flight flight) {
        entityManager.persist(flight);
    }

    @Transactional
    public void updateFlight(Flight flight) {
        Flight  f = entityManager.find(Flight.class, flight.getId());

        f.setAirline(flight.getAirline());
        f.setDepartureDate(flight.getDepartureDate());
        f.setDestination(flight.getDestination());
        f.setOrigin(flight.getOrigin());
        f.setPrice(flight.getPrice());

        entityManager.merge(f);
    }

    @Transactional
    public void deleteFlight(Long id) {
        Flight f = entityManager.find(Flight.class, id);
        entityManager.remove(f);
    }

    @Transactional
    public void createHotel(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Transactional
    public void updateHotel(Hotel hotel) {
        Hotel  h = entityManager.find(Hotel.class, hotel.getId());

        h.setName(hotel.getName());
        h.setCity(hotel.getCity());
        h.setState(hotel.getState());
        h.setZip(hotel.getZip());
        h.setPhone(hotel.getPhone());
        h.setRating(hotel.getRating());

        entityManager.merge(h);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel  h = entityManager.find(Hotel.class, id);
        entityManager.remove(h);
    }
}
