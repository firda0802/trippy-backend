package com.java.pabw.trippy.app.Repository;

import com.java.pabw.trippy.app.entity.Users;

import java.util.list;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findByRoleId(int roleId);

    Optional<Users> findByUserId(int userId);
    Optional<Users> findByEmailIgnoreCaseAndPassword(String email, String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Users a set a.status = false where a.userId =:userId")
    void deleteAkun(@Param("userId") int userId);
    Users findByEmailIgnoreCaseAndRoleId(String emai, int roleId);

    Optional<Users> findByEmailIgnoreCase(String email, Boolean);
}