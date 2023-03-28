package com.java.pabw.trippy.app.models;
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
@Table(name = "room")
public class Room implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    @SequenceGenerator(name = "room_seq", sequenceName = "id_room_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "roomNumber")
    private Integer roomNumber;

    @Column(name = "roomType")
    private Integer roomType;
    @Column(name = "price")
    private Integer price;

    @Column(name = "user_id")
    private int userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;

    @Column(name = "hotel_id")
    private int hotelId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
    private Hotel hotels;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @CreationTimestamp
    @Column(name = "cdate")
    private LocalDateTime cdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @UpdateTimestamp
    @Column(name = "mdate")
    private LocalDateTime mdate;

    public Room(Integer roomNumber,Integer roomType, Integer price) {
        this.roomNumber = roomNumber;
        this.roomType = roomNumber;
        this.price = price;
    }
}
