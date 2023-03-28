package com.java.pabw.trippy.app.service;

import com.java.pabw.trippy.app.models.*;
import java.util.List;
public interface InitializeService {
    List<ClassSeats> listClass();
    void initPricing(Pricing req);
    List<Airport> getAirport();
    void initAirport(Airport airport);
    void initAirplanes(Airplane airplane);

    List<Airplane> getAirplane();
    void initClassSeats(ClassSeats classSeats);

    void initDestinationCity(DestinationCity destinationCity);

    List<DestinationCity> getDestinationCity();
    void initDestination(List<Destination> destination);

    List<Destination> getDestination();

    void initPassengerType(PassengerType req);

    List<PassengerType> getPassengerType();

    void initSchedule(List<Schedule> req);

    void initPayment(Payment payment);
    List<Payment> listPayment();

    List<Citizenship> listCitizenship();

    void initCitizenship(Citizenship req);

    List<Facility> listFacility();

    void initFacility(List<Facility> req);

    List<Users> dataAdmin();

    void initAdmin(Users users);
}
