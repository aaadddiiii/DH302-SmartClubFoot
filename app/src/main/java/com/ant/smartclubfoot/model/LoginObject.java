package com.ant.smartclubfoot.model;

import com.google.gson.annotations.SerializedName;

public class LoginObject {

    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;

    public LoginObject(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
