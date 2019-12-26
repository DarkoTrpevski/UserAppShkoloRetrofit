package com.darko.userappshkoloretrofit.dataservice;

import com.darko.userappshkoloretrofit.datamodel.User;

import java.util.ArrayList;

public class UserResponse {
    private ArrayList<User> userArrayList;

    public UserResponse(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }
    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}