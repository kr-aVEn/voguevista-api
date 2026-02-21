package com.voguevista.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "package_id", nullable = false)
    private Integer packageId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    @Column(name = "num_travelers")
    private Integer numTravelers;

    @Column(nullable = false)
    private String status = "pending";  // pending | approved | rejected
}