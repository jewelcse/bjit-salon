package com.bjit.salon.staff.service.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "salon_staff_activity")
public class StaffActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;

    private Timestamp workingDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private long userId;
    private long staffId;
}
