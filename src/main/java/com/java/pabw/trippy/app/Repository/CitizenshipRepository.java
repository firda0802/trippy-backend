package com.java.pabw.trippy.app.Repository;

import com.java.pabw.trippy.app.models.Citizenship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenshipRepository extends JpaRepository<Citizenship, Integer> {
}