package com.java.pabw.trippy.app.repository;

import com.java.pabw.trippy.app.entity.Citizenship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenshipRepository extends JpaRepository<Citizenship, Integer> {
}