package com.iribeirodev.TestePostgres.repository;

import com.iribeirodev.TestePostgres.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}