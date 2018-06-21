package com.usst.ten.demo.pojo;

import com.usst.ten.demo.entity.Activity;

public class ActivityInfo {
    private String uid;
    private String aid;
    private Activity activity;

    public ActivityInfo() {
    }

    public ActivityInfo(String uid, String aid, Activity activity) {
        this.uid = uid;
        this.aid = aid;
        this.activity = activity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
