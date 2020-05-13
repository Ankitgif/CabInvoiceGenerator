package com.cabinvoicegenerator;

import java.util.ArrayList;



public class Ride extends ArrayList<Ride> {

    public final double distance;
    public final int time;
    public final RideType rideType;

    public enum RideType{
        normal,premium
    }
    public Ride(double distance, int time, RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;

    }
}
