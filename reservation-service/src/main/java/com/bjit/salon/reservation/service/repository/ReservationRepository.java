package com.bjit.salon.reservation.service.repository;


import com.bjit.salon.reservation.service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
