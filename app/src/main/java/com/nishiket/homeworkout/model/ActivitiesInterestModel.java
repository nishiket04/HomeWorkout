package com.nishiket.homeworkout.model;

import android.content.SyncRequest;

public class ActivitiesInterestModel {
    int image;
    String activitie;
    boolean checkBox;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getActivitie() {
        return activitie;
    }

    public void setActivitie(String activitie) {
        this.activitie = activitie;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
