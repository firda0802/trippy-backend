package com.java.pabw.trippy.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.PassengerType;

import org.springframework.stereotype.Repository;

@Repository
public interface PassengerTypeRepository extends JpaRepository<PassengerType, Integer>{
    Optional<PassengerType> findById(Integer passengertypeId);
}
