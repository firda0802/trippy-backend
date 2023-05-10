package com.java.pabw.trippy.app.Repository;
import com.java.pabw.trippy.app.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    List<Facility> findByClassId(int classId);
}
