package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.Pricing;

import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepo extends JpaRepository<Pricing, Integer>{
    Optional<Pricing> findById(Integer pricingId);
}
