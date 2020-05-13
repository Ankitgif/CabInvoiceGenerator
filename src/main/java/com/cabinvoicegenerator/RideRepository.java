package com.cabinvoicegenerator;

import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        boolean isRidePresent = userRides.containsKey(userId);
        if (!isRidePresent) {
            ArrayList<Ride> list = new ArrayList<>(Arrays.asList(rides));
            this.userRides.put(userId, list);
        } else for (Ride ride : rides) userRides.get(userId).add(ride);
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
