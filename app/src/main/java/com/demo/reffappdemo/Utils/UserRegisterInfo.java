package com.demo.reffappdemo.Utils;

import android.support.annotation.Nullable;

public class UserRegisterInfo {

    private String fullname,username,email,password,phoneNumber;

    public UserRegisterInfo(@Nullable String fullname,@Nullable String username,@Nullable String email,@Nullable String password,@Nullable String phoneNumber) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
