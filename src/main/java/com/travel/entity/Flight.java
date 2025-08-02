package com.travel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Calendar;

@Entity
@NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f")
@XmlRootElement
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_generator")
    @SequenceGenerator(name = "travel_generator", sequenceName = "SEQ_TRAVEL")
    private Long id;

    private String airline;
    private String origin;

    @NotEmpty
    private String destination;

    @Temporal(TemporalType.DATE)
    private Calendar departureDate;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
