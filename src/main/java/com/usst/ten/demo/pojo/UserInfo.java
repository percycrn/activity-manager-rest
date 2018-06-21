package com.usst.ten.demo.pojo;

public class UserInfo {
    private String name;
    private String email;
    private String address;

    public UserInfo() {
    }

    public UserInfo(String uid, String email, String address) {
        this.name = uid;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
