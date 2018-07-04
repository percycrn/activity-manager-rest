package com.usst.ten.demo.pojo;

public class SignInResponse {
    private String message;
    private int status;
    private int uid;

    public SignInResponse(String message, int status, int uid) {
        this.message = message;
        this.status = status;
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

