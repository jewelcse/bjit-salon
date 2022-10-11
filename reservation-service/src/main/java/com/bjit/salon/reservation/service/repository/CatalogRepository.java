package com.bjit.salon.reservation.service.repository;

import com.bjit.salon.reservation.service.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
