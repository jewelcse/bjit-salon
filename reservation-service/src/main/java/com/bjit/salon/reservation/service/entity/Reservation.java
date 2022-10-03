package com.bjit.salon.reservation.service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long staffId;
    private long consumerId;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @Column(name = "working_status")
    @Enumerated(EnumType.STRING)
    private EWorkingStatus workingStatus;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private EPaymentMethod paymentMethod;

}
