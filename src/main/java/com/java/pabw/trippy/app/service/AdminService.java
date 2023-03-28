package com.java.pabw.trippy.app.service;
import com.java.pabw.trippy.app.DTO.ReqUpdatePayment;
public interface AdminService {
    void createPayment(ReqUpdatePayment req);
    void disablePayment(int idPayment);
    Boolean updatePayment(int idPayment, ReqUpdatePayment req);
}
