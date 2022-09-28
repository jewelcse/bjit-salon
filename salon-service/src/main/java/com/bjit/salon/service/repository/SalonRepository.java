package com.bjit.salon.service.repository;

import com.bjit.salon.service.entiry.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon,Long> {

}
