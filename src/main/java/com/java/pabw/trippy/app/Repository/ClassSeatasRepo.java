package com.java.pabw.trippy.app.Repository;

import com.java.pabw.trippy.app.entity.ClassSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSeatsRepository extends JpaRepository<ClassSeats, Integer> {
}