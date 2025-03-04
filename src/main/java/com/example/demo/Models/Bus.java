package com.example.demo.Models;

public class Bus {
    private Integer bus_id;
    private String agency;
    private String origin;
    private String destination;

    public Bus(int bus_id, String agency, String origin, String destination) {
        this.bus_id = bus_id;
        this.agency = agency;
        this.origin = origin;
        this.destination = destination;
    }

    public Integer getId() { return bus_id;}
    public String getAgency() { return agency;}
    public String getOrigin() { return origin;}
    public String getDestination() { return destination;}
}
