package com.usst.ten.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(RecordId.class)
public class Record implements Serializable {
    @Id
    private Integer uid;
    @Id
    private Integer aid;
    private String tag;
    private Long signInTime; // 报名时间
    private Long signUpTime; // 签到时间

    public Record() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Long signInTime) {
        this.signInTime = signInTime;
    }

    public Long getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(Long signUpTime) {
        this.signUpTime = signUpTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "uid=" + uid +
                ", aid=" + aid +
                ", tag='" + tag + '\'' +
                ", signInTime=" + signInTime +
                ", signUpTime=" + signUpTime +
                '}';
    }
}
