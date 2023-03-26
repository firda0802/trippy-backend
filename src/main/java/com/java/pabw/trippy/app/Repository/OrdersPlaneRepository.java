package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.OrdersPlane;

import org.springframework.stereotype.Repository;

@Repository
public interface OrdersPlaneRepository extends JpaRepository<OrdersPlane, Integer>{
    Optional<OrdersPlane> findById(Integer idOrder);
}
