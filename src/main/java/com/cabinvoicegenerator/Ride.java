package com.cabinvoicegenerator;

public class Ride   {

    private final double distance;
    private final int time;
    private final CabRide type;

    public Ride(double distance, int time, CabRide type) {
        this.distance = distance;
        this.time = time;
        this.type = type;
    }

    public double getDistanceTime() {
        return type.calculateFare(distance,time);
    }


}
