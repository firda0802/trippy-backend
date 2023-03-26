package com.java.pabw.trippy.app.repository;
import com.java.pabw.trippy.app.entity.Notifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface NotificationRepository extends JpaRepository<Notifications,Integer>{
    @Query("select count(*) as jumlah from Notifications a where a.status = true and a.userId = :userId")
    Notifications getCount(@Param("userId") int userId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Notifications a set a.status = false where a.userId =:userId")
    void clearNotif(@Param("userId") int userId);

    Page<Notifications> findByUserId(int userId, Pageable paging);
}
