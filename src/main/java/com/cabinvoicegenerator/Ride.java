package com.cabinvoicegenerator;

import java.util.ArrayList;


public class Ride extends ArrayList<Ride> {
    public final double distance;
    public final int time;
    public String rideType;

    public Ride(double distance, int time, String rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;

    }
}
