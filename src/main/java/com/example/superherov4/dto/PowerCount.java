package com.example.superherov4.dto;

import java.util.List;

public class PowerCount {
    private String heroName;
    private int countPower;
    private List<Integer> countPowerInt;

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

    public List<Integer> getCountPowerInt() {
        return countPowerInt;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setCountPower(int countPower) {
        this.countPower = countPower;
    }

    public void setCountPowerInt(List<Integer> countPowerInt) {
        this.countPowerInt = countPowerInt;
    }

    public void addPowers(int power) {
        countPowerInt.add(power);
    }
}
