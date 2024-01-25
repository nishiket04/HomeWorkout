package com.nishiket.homeworkout.model;

import java.util.List;

public class NotificationParentModel {
    List<NotificationChildModel> notificationChildModelList;
    String notifiacationTime;

    public List<NotificationChildModel> getNotificationChildModelList() {
        return notificationChildModelList;
    }

    public void setNotificationChildModelList(List<NotificationChildModel> notificationChildModelList) {
        this.notificationChildModelList = notificationChildModelList;
    }

    public String getNotifiacationTime() {
        return notifiacationTime;
    }

    public void setNotifiacationTime(String notifiacationTime) {
        this.notifiacationTime = notifiacationTime;
    }
}
