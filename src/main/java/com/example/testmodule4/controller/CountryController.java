package com.example.testmodule4.controller;

import com.example.testmodule4.model.City;
import com.example.testmodule4.model.Country;
import com.example.testmodule4.service.impl.CityServiceImpl;
import com.example.testmodule4.service.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryServiceImpl cityService;

    @GetMapping
    public ResponseEntity<Iterable<Country>> findAll() {
        List<Country> cities = (List<Country>) cityService.findAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Optional<Country> city = cityService.findById(id);
        return city.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }





}
