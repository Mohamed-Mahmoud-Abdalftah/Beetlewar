package com.beetleware.task.ui.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestLogin {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;


    public requestLogin(String username,String password) {
        this.username = username;
        this.password = password;
    }
}
