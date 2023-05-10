package com.java.pabw.trippy.app.service;
import com.java.pabw.trippy.app.dto.ReqUpdatePayment;

public interface AdminService {
    void createPayment(ReqUpdatePayment req);
    void disablePayment(int idPayment);
    Boolean updatePayment(int idPayment, ReqUpdatePayment req);
}
