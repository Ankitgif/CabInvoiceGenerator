package com.cabinvoicegenerator;

public class InvoiceService {


    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;
    private static final double MINIMUM_COST_PER_KILOMETER_PREMIUM = 15;
    private static final int COST_PER_TIME_PRIMIUM = 2;
    private static final double MINIMUM_FARE_PREMIUM = 20;
    private  RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, String rideType) {
        if (rideType == "normal") {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_FARE);
        } else{
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_PREMIUM + time * COST_PER_TIME_PRIMIUM;
            return Math.max(totalFare, MINIMUM_FARE_PREMIUM);
        }
    }
//    public double calculateFare(double distance, int time, String rideType) {
//        double totalFare = distance * MINIMUM_COST_PER_KILOMETER_PREMIUM + time * COST_PER_TIME_PRIMIUM;
//        return Math.max(totalFare, MINIMUM_FARE_PREMIUM);
//    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.distance,ride.time,ride.rideType);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) throws CabInvoiceException {
        if (userId == null)
            throw new CabInvoiceException("UserId cant be Null", CabInvoiceException.ExceptionType.USER_CANT_BE_NULL);
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) throws CabInvoiceException {
        if (userId == null)
            throw new CabInvoiceException("UserId cant be Null", CabInvoiceException.ExceptionType.USER_CANT_BE_NULL);
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
