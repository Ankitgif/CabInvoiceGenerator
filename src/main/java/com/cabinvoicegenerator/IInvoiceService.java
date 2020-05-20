package com.cabinvoicegenerator;

public interface IInvoiceService {

    void addRides(String userId, Ride[] rides) throws CabInvoiceException;

    InvoiceSummary getInvoiceSummary(String userId) throws CabInvoiceException;
}
