package com.java.pabw.trippy.app.models;
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
@Table(name = "details_booking_hotel")
public class DetailsBookingHotel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_details_seq")
    @SequenceGenerator(name = "booking_details_seq", sequenceName = "id_booking_details_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id_booking_details", nullable = false)
    private Integer idBookingDetails;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "title")
    private String title;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "description")
    private String description;

    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "booking_id", insertable = false, updatable = false)
    private BookingHotel bookings;

    @Column(name = "schedule")
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedules;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @CreationTimestamp
    @Column(name = "cdate")
    private LocalDateTime cdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @UpdateTimestamp
    @Column(name = "mdate")
    private LocalDateTime mdate;
}
