package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.OrdersDetailsPlane;

import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailsPlaneRepo extends JpaRepository<OrdersDetailsPlane, Integer>{
    Optional<OrdersDetailsPlane> findById(Integer idOrderDetails);
}
