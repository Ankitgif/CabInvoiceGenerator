package com.cabinvoicegenerator;

public class InvoiceService {

    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, CabRide type) {
        Ride ride = new Ride(distance, time, type);
        return type.calculateFare(ride);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.cabRide.calculateFare(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
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
