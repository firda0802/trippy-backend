package com.java.pabw.trippy.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hotel")
public class Hotel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
    @SequenceGenerator(name = "hotel_seq", sequenceName = "id_hotel_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "hotel_id", nullable = false)
    private Integer hotelId;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_no")
    private String phoneNo;
//    @Column(name = "image")
//    private  image;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "description")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @CreationTimestamp
    @Column(name = "cdate")
    private LocalDateTime cdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @UpdateTimestamp
    @Column(name = "mdate")
    private LocalDateTime mdate;

    @Column(name = "user_id")
    private int userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;
}
