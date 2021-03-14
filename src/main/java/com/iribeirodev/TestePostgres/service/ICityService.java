package com.iribeirodev.TestePostgres.service;

import com.iribeirodev.TestePostgres.model.City;
import java.util.List;
import java.util.Optional;

public interface ICityService {
    List<City> findAll();
    Optional<City> findCity(Long id);
    City createCity(City newCity);
    City updateCity(City updatedCity, Long id);
    City deleteCity(Long id);
}