package com.example.testmodule4.repository;

import com.example.testmodule4.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<Country, Long> {
}
