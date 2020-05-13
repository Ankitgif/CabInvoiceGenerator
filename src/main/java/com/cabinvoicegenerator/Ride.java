package com.cabinvoicegenerator;

import java.util.ArrayList;

public class Ride extends ArrayList<Ride> {

    public final double distance;
    public final int time;
    public CabRide cabRide;

    public Ride(double distance, int time, CabRide cabRide) {
        this.distance = distance;
        this.time = time;
        this.cabRide = cabRide;
    }
}
