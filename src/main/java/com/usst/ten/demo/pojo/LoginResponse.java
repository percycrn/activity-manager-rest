package com.usst.ten.demo.pojo;

import com.usst.ten.demo.entity.Activity;
import com.usst.ten.demo.entity.User;

import java.util.List;

public class LoginResponse {
    private String errorMessage;
    private User user;
    private List<Activity> joinedActivities;

    public LoginResponse() {
    }

    public LoginResponse( String errorMessage, User user, List<Activity> joinedActivities) {
        this.errorMessage = errorMessage;
        this.user = user;
        this.joinedActivities = joinedActivities;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Activity> getJoinedActivities() {
        return joinedActivities;
    }

    public void setJoinedActivities(List<Activity> joinedActivities) {
        this.joinedActivities = joinedActivities;
    }
}
