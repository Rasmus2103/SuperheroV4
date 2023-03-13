package com.example.superherov4.dto;

import java.util.List;

public class PowerCount {
    private String heroName;
    private int countPower;

    public PowerCount(String heroName, int countPower) {
        this.heroName = heroName;
        this.countPower = countPower;
    }

    public PowerCount() {

    }

    public String getHeroName() {
        return heroName;
    }


    public int getCountPower() {
        return countPower;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setCountPower(int countPower) {
        this.countPower = countPower;
    }

}
