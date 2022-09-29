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
    private double reviews;

    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "salon_id", referencedColumnName = "id")
    private User user;
    // todo: salon staffs(users) list: m <-> m
    @ManyToMany
    private List<User> staffs;
    // todo: salon consumers(users) list: m <-> m
    @ManyToMany
    private List<User> consumers;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private String contractNumber;
    // todo: salon services(services) list: m <-> m



}
