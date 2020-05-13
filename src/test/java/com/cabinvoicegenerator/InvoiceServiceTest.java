package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;

    @Before
    public void setUp() throws Exception{
        invoiceService = new InvoiceService();
    }
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance,time,Ride.RideType.normal);
        Assert.assertEquals(25,fare,0.0);
    }
    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance,time,Ride.RideType.normal);
        Assert.assertEquals(5,fare,0.0) ;
    }
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary(){
        Ride[] rides = {new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedinvoiceSummary,summary);
    }
    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "abc@.com";
        Ride[] rides = {new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal)
        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedinvoiceSummary,summary);

    }
    @Test
    public void givenNullUserIdAndRides_ShouldThrowCustomException() throws CabInvoiceException{
        String userId = null;
        Ride[] rides = {new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal)
        };
        try {
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        }catch (CabInvoiceException exception){
            Assert.assertEquals("UserId cant be Null",exception.getMessage());
        }
    }
    @Test
    public void givenMultipleRidesArrays_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "ankit@.com";
        Ride[] rides1 = {
                new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal)
        };
        invoiceService.addRides(userId, rides1);
        Ride[] rides2 = {
                new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal),
        };
        invoiceService.addRides(userId, rides2);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 60);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
    @Test
    public void givenMultipleUserRidesArrays_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId1 = "ankit@.com";
        Ride[] rides1 = {
                new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal)
        };
        invoiceService.addRides(userId1, rides1);
        String userId2 = "Biru@.com";
        Ride[] rides2 = {
                new Ride(2.0, 5,Ride.RideType.normal),
                new Ride(0.1, 1,Ride.RideType.normal),
        };
        invoiceService.addRides(userId2, rides2);
        InvoiceSummary summary1 = invoiceService.getInvoiceSummary(userId1);
        InvoiceSummary summary2 = invoiceService.getInvoiceSummary(userId2);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, summary1);
        Assert.assertEquals(expectedInvoiceSummary, summary2);
    }
    @Test
    public void givenDistanceAndTime_WithPremiumRide_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance,time,Ride.RideType.premium);
        Assert.assertEquals(40,fare,0.0);
    }
    @Test
    public void givenLessDistanceOrTime_WithPremiumRide_ShouldReturnMinFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance,time,Ride.RideType.premium);
        Assert.assertEquals(20,fare,0.0) ;
    }
}
