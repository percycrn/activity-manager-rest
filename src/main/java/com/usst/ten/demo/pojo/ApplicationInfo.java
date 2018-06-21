package com.usst.ten.demo.pojo;

public class ApplicationInfo {
    private String uid;
    private String tag;
    private String applyTime;
    private String state;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String uid, String tag, String applyTime, String state) {
        this.uid = uid;
        this.tag = tag;
        this.applyTime = applyTime;
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
