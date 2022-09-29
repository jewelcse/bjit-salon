package com.bjit.salon.service.entiry;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "salons")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;
    private String name;
    private String description;
    private String address;
    private long userId;
    private double reviews;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private String contractNumber;
}
