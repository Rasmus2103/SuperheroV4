package com.example.superherov4.dto;

import java.util.List;

public class SuperPowerCount {
    private String heroName;
    private String realName;
    private List<String> powers;

    public SuperPowerCount(String heroName, String realName, List<String> powers, int count) {
        this.heroName = heroName;
        this.realName = realName;
        this.powers = powers;
    }

    public SuperPowerCount() {

    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void addSuperPower(String name) {
        powers.add(name);
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

}
