package com.nishiket.homeworkout.model;

public class WorkoutInfoModel {
//    "id"=>$id,"detail"=>$detail,"cal"=>$cal,"equipment"=>$equipment,"warmup"=>$warmup,"workouts"=>$workouts
    int id;
    String detail;
    String cal;
    String equipment;
    String warmup;
    String workouts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getWarmup() {
        return warmup;
    }

    public void setWarmup(String warmup) {
        this.warmup = warmup;
    }

    public String getWorkouts() {
        return workouts;
    }

    public void setWorkouts(String workouts) {
        this.workouts = workouts;
    }
}
