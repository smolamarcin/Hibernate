package com.smola.hiber.controllers;

import com.smola.hiber.model.CoordinatesSQL;
import com.smola.hiber.repositories.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoordinatesController {
    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @PutMapping(value = "/coordinatesSQL")
    CoordinatesSQL createCoordinate(@RequestBody CoordinatesSQL coordinatesSQL) {
        return this.coordinatesRepository.save(coordinatesSQL);
    }
}
