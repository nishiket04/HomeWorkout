package com.nishiket.homeworkout.model;

import java.util.List;

public class MyWorkoutParentModel {
    String Date,workout;
    List<MyWorkoutChildModel> myWorkoutChildModelsList;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public List<MyWorkoutChildModel> getMyWorkoutChildModelsList() {
        return myWorkoutChildModelsList;
    }

    public void setMyWorkoutChildModelsList(List<MyWorkoutChildModel> myWorkoutChildModelsList) {
        this.myWorkoutChildModelsList = myWorkoutChildModelsList;
    }
}
