package com.example.superherov4.repositories;

import com.example.superherov4.dto.City;
import com.example.superherov4.dto.PowerCount;
import com.example.superherov4.dto.SuperPowerCount;
import com.example.superherov4.model.Superhero;

import java.util.List;

public interface ISuperheroRepo {
    List<Superhero> getSuperheroes();
    Superhero getSuperhero(String name);
    List<SuperPowerCount> getHeroAndPowers();
    PowerCount getPowerCount(String name);
    List<City> getCity();
}
