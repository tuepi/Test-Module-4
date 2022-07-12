package com.example.testmodule4.controller;

import com.example.testmodule4.model.City;
import com.example.testmodule4.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public ResponseEntity<Iterable<City>> findAll() {
        List<City> cities = (List<City>) cityService.findAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        return city.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(cityOptional.get().getId());
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(cityOptional.get(),HttpStatus.OK);
    }


}
