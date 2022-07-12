package com.example.testmodule4.service.impl;

import com.example.testmodule4.model.Country;
import com.example.testmodule4.repository.ICityRepository;
import com.example.testmodule4.repository.ICountryRepository;
import com.example.testmodule4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {
    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
