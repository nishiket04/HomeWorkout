package com.nishiket.homeworkout.model;

public class CustomWorkoutModel {
    String email;
    String name;
    int level;
    String equipment;
    int wormup;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getWormup() {
        return wormup;
    }

    public void setWormup(int wormup) {
        this.wormup = wormup;
    }
}
