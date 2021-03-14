package com.iribeirodev.TestePostgres.controller;

import com.iribeirodev.TestePostgres.dto.NewCityRequest;
import com.iribeirodev.TestePostgres.model.City;
import com.iribeirodev.TestePostgres.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/cities")
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping()
    public ResponseEntity<List<City>> findCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findCity(@PathVariable long id) {
        var city = cityService.findCity(id);
        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    // New City
    // We respond with a semantically clear HTTP status and, if creation succeeded, a URI to the new resource.
    public ResponseEntity<City> create(@RequestBody NewCityRequest city) throws URISyntaxException {
        City cityToCreate = new City();
        cityToCreate.setName(city.getName());
        cityToCreate.setPopulation(city.getPopulation());

        City createdCity = cityService.createCity(cityToCreate);
        if (createdCity == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdCity.getId())
                        .toUri();
            return ResponseEntity.created(uri).body(createdCity);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@RequestBody NewCityRequest city, @PathVariable Long id) {
        City cityToUpdate = new City();
        cityToUpdate.setName(city.getName());
        cityToUpdate.setPopulation(city.getPopulation());

        City updatedCity = cityService.updateCity(cityToUpdate, id);
        if (updatedCity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable Long id) {
        City deletedCity = cityService.deleteCity(id);
        if (deletedCity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedCity);
    }
}