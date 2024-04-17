package com.nishiket.homeworkout.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ExercisesModel {
    private String image;
    private String exercises;
    private long id;
    private String time;

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getExercises() { return exercises; }
    public void setExercises(String value) { this.exercises = value; }

    public int getid() { return Integer.parseInt(String.valueOf(id)); }
    public void setid(long value) { this.id = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

}
