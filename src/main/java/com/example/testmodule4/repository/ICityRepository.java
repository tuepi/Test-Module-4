package com.example.testmodule4.repository;

import com.example.testmodule4.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Long> {
}
