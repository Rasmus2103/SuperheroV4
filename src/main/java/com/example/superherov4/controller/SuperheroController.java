package com.example.superherov4.controller;

import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.ISuperheroRepo;
import com.example.superherov4.repositories.SuperheroRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<String> getSuperheroes(@PathVariable String name) {
        Superhero superhero = superheroRepo.getSuperhero(name);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","text/html");

        return new ResponseEntity<String>(
                "<html><body><h1>" +
                        superhero.getHeroName() + " " +
                        superhero.getRealName() + " " +
                        superhero.getCreationYear() + " " +
                        "</h1></body></html>"
                ,responseHeaders, HttpStatus.OK);
    }
}
