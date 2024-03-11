package com.nishiket.homeworkout.model;

public class CategoryModel {
    int id;
    String category;
    long workouts;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getWorkouts() {
        return workouts;
    }

    public void setWorkouts(long workouts) {
        this.workouts = workouts;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
