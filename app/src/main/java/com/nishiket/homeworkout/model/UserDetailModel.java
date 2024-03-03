package com.nishiket.homeworkout.model;
import java.time.LocalDate;

public class UserDetailModel {
    private long number;
    private String gender;
    private long weightKg;
    private long heightcm;
    private String name;
    private String birth;
    private long goalweightKg;
    private String goale;
    private long traininglevel;
    private String email;

    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public long getWeightKg() { return weightKg; }
    public void setWeightKg(long value) { this.weightKg = value; }

    public long getHeightcm() { return heightcm; }
    public void setHeightcm(long value) { this.heightcm = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getBirth() { return birth; }
    public void setBirth(String value) { this.birth = value; }

    public long getGoalweightKg() { return goalweightKg; }
    public void setGoalweightKg(long value) { this.goalweightKg = value; }

    public String getGoale() { return goale; }
    public void setGoale(String value) { this.goale = value; }

    public long getTraininglevel() { return traininglevel; }
    public void setTraininglevel(long value) { this.traininglevel = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
