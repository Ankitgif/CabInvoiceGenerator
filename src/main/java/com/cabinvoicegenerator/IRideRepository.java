package com.cabinvoicegenerator;

public interface IRideRepository {

    void addRides(String userId, Ride[] rides);

    Ride[] getRides(String userId);
}
