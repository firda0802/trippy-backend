package com.java.pabw.trippy.app.Repository;

import com.java.pabw.trippy.app.entity.Citizenship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenshipRepo extends JpaRepository<Citizenship, Integer> {
}