package com.java.pabw.trippy.app.service;

import com.java.pabw.trippy.app.models.Payment;
import com.java.pabw.trippy.app.Repository.PaymentRepository;
import com.java.pabw.trippy.app.dto.ReqUpdatePayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void createPayment(ReqUpdatePayment req) {
        Payment newPayment = new Payment();
        newPayment.setPaymentMethod(req.getPaymentMethod());
        newPayment.setStatus(Boolean.TRUE);
        paymentRepository.saveAndFlush(newPayment);
    }

    @Override
    public void disablePayment(int idPayment) {
        paymentRepository.disablePayment(idPayment);
    }

    @Override
    public Boolean updatePayment(int idPayment, ReqUpdatePayment req) {
        Optional<Payment> cek = paymentRepository.findById(idPayment);
        if(cek.isPresent()){
            Payment p = cek.get();
            p.setPaymentMethod(req.getPaymentMethod());
            paymentRepository.saveAndFlush(p);
            return true;
        }else{
            return false;
        }
    }

}
