package com.bjit.salon.service.repository;

import com.bjit.salon.service.entiry.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
