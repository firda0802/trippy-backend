package com.java.pabw.trippy.app.Repository;
import com.java.pabw.trippy.app.models.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface SeatsRepository extends JpaRepository<Seats, Integer>{
    Seats findByClassIdAndAirplanesIdAndSeatsNumber(int classId, int airplaneId, String seatsNumber);

    Optional<Seats> findById(Integer seatsId);
}
