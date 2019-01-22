package com.smola.hiber.repositories;

import com.smola.hiber.model.CoordinatesSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesSQL,Long> {
}
