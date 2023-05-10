package com.java.pabw.trippy.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.Payment;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findById(Integer paymentId);

    void disablePayment(int idPayment);

    List<Payment> findByStatus(Boolean aTrue);
}
