package com.java.pabw.trippy.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "booking_hotel")
public class BookingHotel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "id_booking_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id_booking", nullable = false)
    private Integer idBooking;
    @Id
    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;

    @Column(name = "room_id")
    private int roomId;

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room rooms;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @CreationTimestamp
    @Column(name = "cdate")
    private LocalDateTime cdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @UpdateTimestamp
    @Column(name = "mdate")
    private LocalDateTime mdate;
}
