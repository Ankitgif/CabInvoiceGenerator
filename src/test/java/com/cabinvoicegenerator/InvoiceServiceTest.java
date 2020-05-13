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
        double fare = invoiceService.calculateFare(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }
    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance,time);
        Assert.assertEquals(5,fare,0.0) ;
    }
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary(){
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedinvoiceSummary,summary);
    }
    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "abc@.com";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedinvoiceSummary,summary);

    }
    @Test
    public void givenNullUserIdAndRides_ShouldThrowCustomException() throws CabInvoiceException{
        String userId = null;
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        try {
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        }catch (CabInvoiceException exception){
            Assert.assertEquals("UserId cant be Null",exception.getMessage());
        }
    }
}
