package com.iribeirodev.TestePostgres.service;

import java.util.List;
import java.util.Optional;

import com.iribeirodev.TestePostgres.model.City;
import com.iribeirodev.TestePostgres.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {
    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {
        var cities = (List<City>) repository.findAll();
        return cities;
    }

    @Override
    public Optional<City> findCity(Long id) {
        return repository.findById(id);
    }

    @Override
    public City createCity(City newCity) {
        return repository.save(newCity);
    }

    @Override
    public City updateCity(City updatedCity, Long id) {
        var city = repository.findById(id);
        if (city.isPresent()) {
            var cityToUpdate = city.get();
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setPopulation(updatedCity.getPopulation());
            return repository.save(cityToUpdate);
        }
        return null;
    }

    @Override
    public City deleteCity(Long id) {
        var city = repository.findById(id);
        if (city.isPresent()) {
            repository.deleteById(id);
            return city.get();
        }
        return null;
    }
}