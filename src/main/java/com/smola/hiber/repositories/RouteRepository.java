package com.smola.hiber.repositories;

import com.smola.hiber.model.RouteSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteSQL, Long> {
}
