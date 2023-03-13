package com.example.superherov4.repositories;

import com.example.superherov4.dto.City;
import com.example.superherov4.dto.PowerCount;
import com.example.superherov4.dto.SuperPowerCount;
import com.example.superherov4.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("superhero_DB")
public class SuperheroRepo implements ISuperheroRepo {
    private String SQL;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

    @Value("${spring.datasource.url}")
            private String db_url;

    @Value("${spring.datasource.username}")
            private String uid;

    @Value("${spring.datasource.password}")
            private String pwd;

    public Connection connect() {
        Connection con;
        try {
            con = DriverManager.getConnection(db_url, uid, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public List<Superhero> getSuperheroes() {
        List<Superhero> superheroes = new ArrayList<>();
        try {
            SQL = "SELECT * FROM superhero ORDER BY heroname ASC;";
            stmt = connect().createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                String heroName = rs.getString("heroname");
                String realName = rs.getString("realname");
                int creationYear = rs.getInt("creationyear");
                superheroes.add(new Superhero(heroName, realName, creationYear));
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Superhero getSuperhero(String name) {
        Superhero superheroObj = null;
        try {
            SQL = "SELECT * FROM superhero WHERE heroname = ?;";
            ps = connect().prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(rs.next()) {
                String heroName = rs.getString("heroname");
                String realName = rs.getString("realname");
                int creationYear = rs.getInt("creationyear");
                superheroObj = new Superhero(heroName, realName, creationYear);
            }
            return superheroObj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPowerCount> getHeroAndPowers() {
        List<SuperPowerCount> superheroes = new ArrayList<>();
        try {
            SQL = "SELECT heroname, realname, powername FROM superhero " +
                    "JOIN superheropower ON superhero.id = superheropower.superheroid " +
                    "JOIN superpower ON superheropower.superpowerid  = superpower.id";
            ps = connect().prepareStatement(SQL);
            rs = ps.executeQuery();

            while(rs.next()) {
                SuperPowerCount currentPower = new SuperPowerCount();

                String heroName = rs.getString("heroname");
                String realName = rs.getString("realname");
                String powerName = rs.getString("powername");

                    List<String> powers = new ArrayList<>();
                    currentPower.setPowers(powers);
                    currentPower.addSuperPower(powerName);
                    currentPower.setHeroName(heroName);
                    currentPower.setRealName(realName);

                    superheroes.add(currentPower);
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public PowerCount getPowerCount(String name) {
        PowerCount countObj = null;
        try {
            SQL = "SELECT heroname, COUNT(*) AS powers FROM superhero JOIN superheropower ON superhero.id = superheropower.superheroid AND heroname = ?; ";
            ps = connect().prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(rs.next()) {
                String heroName = rs.getString("heroname");
                int count = rs.getInt("powers");
                countObj = new PowerCount(heroName, count);
            }
            return countObj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<City> getCity() {
        List<City> cities = new ArrayList<>();
        City currentCity = null;
        String currentCityName = "";
        try {
            SQL = "SELECT city.cityname, superhero.heroname FROM superhero JOIN city ON superhero.cityid = city.id ORDER BY city.cityname";
            ps = connect().prepareStatement(SQL);
            rs = ps.executeQuery();

            while(rs.next()) {
                String cityName = rs.getString("cityname");
                String heroName = rs.getString("heroname");
                if (currentCityName.equals(cityName)) {
                    currentCity.addHero(heroName);
                } else {
                    currentCity = new City(cityName, new ArrayList<>());
                    currentCityName = cityName;
                    currentCity.addHero(heroName);
                }
                if(!cities.contains(currentCity))
                cities.add(currentCity);
            }
            return cities;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
