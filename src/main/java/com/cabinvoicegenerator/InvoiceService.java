package com.cabinvoicegenerator;

public class InvoiceService implements IInvoiceService {

    private IRideRepository rideRepository;

    public InvoiceService() {

    }

    public InvoiceService(IRideRepository rideRepository) {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, CabRide type) {
        Ride ride = new Ride(distance, time, type);
        return type.calculateFare(distance, time);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.getDistanceTime();
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
