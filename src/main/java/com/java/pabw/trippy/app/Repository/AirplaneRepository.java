package com.java.pabw.trippy.app.repository;
import com.java.pabw.trippy.app.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface AirplaneRepository extends JpaRepository<Airplane, Integer>{
    Optional<Airplane> findById(Integer airplaneId);
}
