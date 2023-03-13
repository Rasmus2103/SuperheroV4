package com.example.superherov4.controller;

import com.example.superherov4.dto.City;
import com.example.superherov4.dto.PowerCount;
import com.example.superherov4.dto.SuperPowerCount;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.ISuperheroRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class SuperheroController {
    private ISuperheroRepo superheroRepo;

    public SuperheroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl) {
        superheroRepo = (ISuperheroRepo) context.getBean(impl);
    }

    @GetMapping(path="superheroes")
    public ResponseEntity<List<Superhero>> getSuperheroes() {
        List superheroList = superheroRepo.getSuperheroes();
        return new ResponseEntity<List<Superhero>>(superheroList, HttpStatus.OK);
    }

    @GetMapping(path="superheroes/{name}")
    public ResponseEntity<Superhero> getSuperheroes(@PathVariable String name) {
        Superhero superhero = superheroRepo.getSuperhero(name);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @GetMapping(path="superheroes/superpower")
    public ResponseEntity<List<SuperPowerCount>> getHeroAndPower() {
        List superheroList = superheroRepo.getHeroAndPowers();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping(path="superheroes/count")
    public ResponseEntity<List<PowerCount>> getAllPowerCount() {
        List superheroList = superheroRepo.getAllPowerCount();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping(path="superheroes/count/{name}")
    public ResponseEntity<PowerCount> getPowerCount(@PathVariable String name) {
        PowerCount count = superheroRepo.getPowerCount(name);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(path="superheroes/city")
    public ResponseEntity<List<City>> getCity() {
        List cityList = superheroRepo.getCity();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
}
