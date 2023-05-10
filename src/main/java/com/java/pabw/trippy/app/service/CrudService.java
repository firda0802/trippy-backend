package com.java.pabw.trippy.app.service;

import java.util.List;

import com.java.pabw.trippy.app.models.Airplane;
import com.java.pabw.trippy.app.models.Payment;
import com.java.pabw.trippy.app.models.Schedule;
import com.java.pabw.trippy.app.models.Seats;

public class CrudService {
    List<Airplane> findAllAirplanes();

    Airplane saveAirplane(ReqCreateAirplane airplane);

    Boolean updateAirplane(Integer airplaneId, ReqCreateAirplane airplane);

    Boolean deleteAirplane(Integer airplaneId);

    Boolean updateAirport(Integer id, String name, String address, int cityId);

    Boolean deleteAirport(Integer id);

    List<Payment> findAllPayment();

    Payment savePayment(ReqCreatePayment payment);

    Boolean updatePayment(Integer paymentId, ReqCreatePayment payment);

    Boolean deletePayment(Integer paymentId);

    List<Schedule> findAllSchedule(int limit, int offset);

    Schedule saveSchedule(ReqCreateSchedule schedule);

    Boolean updateSchedule(Integer scheduleId, ReqCreateSchedule schedule);

    Boolean deleteSchedule(Integer scheduleId);

    List<Seats> findAllSeats();

    Seats saveSeats(ReqCreateSeats seats);

    Boolean updateSeats(Integer seatsId, ReqCreateSeats seats);

    Boolean deleteSeats(Integer seatsId);

    Boolean updateAddClass(ReqUpdateClass req);

    Boolean deleteClassSeats(int id);

    Boolean updateAddDestination(ReqUpdateDestination req);

    Boolean deleteDestination(int id);

}
