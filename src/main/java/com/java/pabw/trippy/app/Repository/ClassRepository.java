package com.java.pabw.trippy.app.Repository;
import com.java.pabw.trippy.app.models.ClassSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassRepository extends JpaRepository<ClassSeats, Integer>{
}
