package com.example.rickandmorty.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    int responseCode = 0;

    public static final int NO_INTERNET = 500;
    public static final int FAILURE = 400;
    public static final int SUCCESS = 200;

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public int getResponseCode() {
        return responseCode;
    }
}
