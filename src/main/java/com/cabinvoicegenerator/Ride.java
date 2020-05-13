package com.cabinvoicegenerator;

import java.util.ArrayList;

public class Ride extends ArrayList<Ride> {
    public final double distance;
    public final int time;

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }
}
